#include <atomic>
#include <chrono>
#include <thread>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <unistd.h>
#include "support.h"
#include "shash2.h"
#include "tree.h"

/// help() - Print a help message
void help(char *progname) {
    using std::cout;
    using std::endl;
    cout << "Usage: " << progname << " [OPTIONS]" << endl;
    cout << "Execute a concurrent set microbenchmark" << endl;
    cout << "  -k    key range for elements in the list" << endl;
    cout << "  -o    operations per thread" << endl;
    cout << "  -b    # buckets for hash tests" << endl;
    cout << "  -r    ratio of lookup operations" << endl;
    cout << "  -t    number of threads" << endl;
    cout << "  -p    number of operations per iteration" << endl;
    cout << "  -n    test name" << endl;
    cout << "        [h]ash, [t]ree" << endl;
}

/// bench() - kick off a timed microbenchmark run
template <class settype>
void bench(unsigned keyrange, unsigned iters, unsigned buckets, unsigned ratio, unsigned nthreads, unsigned ops) {
    using std::cout;
    using std::endl;
    using std::chrono::duration_cast;
    using std::chrono::duration;

    // create a linked list and fill it with half the elements in the range
    settype my_set(buckets);
    for (int i = 0; i < keyrange; i += 2) {
        int val = i;
        bool res = false;
        my_set.insert(&val, &res, 1);
    }

    // three barriers, for thread start, benchmark start, and benchmark end
    std::atomic<int> barrier_1, barrier_2, barrier_3;
    barrier_1 = 0;
    barrier_2 = 0;
    barrier_3 = 0;

    // timers for benchmark start and end
    std::chrono::high_resolution_clock::time_point start_time, end_time;

    // this is the benchmark task
    auto task = [&](int id) {
        // set random seed for this thread
        unsigned seed = id;

        // arrive at first barrier... "ready to run"
        barrier_1++;
        while (barrier_1 < nthreads) { }

        // get the timer, then arrive at the "start running" barrier
        if (id == 0)
            start_time = std::chrono::high_resolution_clock::now();
        barrier_2++;
        while (barrier_2 < nthreads) { }

        // Do random operations according to the ratio
        for (int i = 0; i < iters; ++i) {
            int action = rand_r(&seed) % 100;
            int vals[ops];
            bool res[ops];
            for (int j = 0; j < ops; ++j) {
                vals[j] = rand_r(&seed) % keyrange;
                res[j] = false;
            }
            if (action < ratio) {
                my_set.lookup(vals, res, ops);
            }
            else if (action < ratio + (100 - ratio)/2) {
                my_set.insert(vals, res, ops);
            }
            else {
                my_set.remove(vals, res, ops);
            }
        }

        // arrive at the last barrier, then get the timer again
        barrier_3++;
        while (barrier_3 < nthreads) { }
        if (id == 0)
            end_time = std::chrono::high_resolution_clock::now();
    };

    // create the threads, and then wait for them to finish
    std::vector<std::thread> threads;
    for (int i = 0; i < nthreads; ++i) {
        threads.push_back(std::thread(task, i));
    }
    for (int i = 0; i < nthreads; ++i) {
        threads[i].join();
    }

    // print the benchmark throughput
    auto elapsed =
        duration_cast<duration<double>>(end_time - start_time).count();
    cout << "Throughput: " << ((iters * nthreads) / elapsed) << endl;
}

/// main() - parse command line, create a socket, handle requests
int main(int argc, char **argv) {
    using std::cout;
    using std::endl;
    // for getopt
    long opt;
    // parameters
    unsigned keyrange = 256;
    unsigned iters      = 1048576;
    unsigned buckets  = 16;
    unsigned ratio    = 80;
    unsigned threads  = 2;
    unsigned ops      = 256;
    char test         = 'h';

    check_team(argv[0]);

    // parse the command-line options.  see help() for more info
    while ((opt = getopt(argc, argv, "hk:o:b:r:t:p:n:")) != -1) {
        switch(opt) {
          case 'h': help(argv[0]);           return 0;
          case 'k': keyrange = atoi(optarg); break;
          case 'o': iters    = atoi(optarg); break;
          case 'b': buckets  = atoi(optarg); break;
          case 'r': ratio    = atoi(optarg); break;
          case 't': threads  = atoi(optarg); break;
          case 'p': ops      = atoi(optarg); break;
          case 'n': test     = optarg[0];    break;
        }
    }

    // print the configuration
    cout << "Configuration: " << endl;
    cout << "  key range:            " << keyrange << endl;
    cout << "  iters/thread:         " << iters << endl;
    cout << "  buckets:              " << buckets << endl;
    cout << "  lookup/insert/remove: " << ratio << "/" << (100 - ratio)/2 << "/" << (100 - ratio) / 2 << endl;
    cout << "  threads:              " << threads << endl;
    cout << "  ops/iter:             " << ops << endl;
    cout << "  test name:            " << test << endl;

    // run the microbenchmark
    if (test == 'h') {
        bench<shash2>(keyrange, iters, buckets, ratio, threads, ops);
    }
    else if (test == 't') {
        bench<tree>(keyrange, iters, buckets, ratio, threads, ops);
    }
    exit(0);
}
