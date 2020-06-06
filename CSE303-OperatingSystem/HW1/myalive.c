#include <getopt.h>
#include <stdio.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>
#include <sys/time.h>
#include "support.h"
#include <unistd.h>


void sigint_handler(int);
void sigalrm_handler(int);
volatile int printStr, killCommand;

/*
 * alive() - install some signal handlers, set an alarm, and wait...
 */
void alive(void) {
    /* TODO: Complete this function */
    /* Note: you will probably need to implement some other functions */
    struct timeval start, end;
    gettimeofday(&start, NULL);

    signal(SIGINT, sigint_handler);
    signal(SIGALRM, sigalrm_handler);
    alarm(10);


    while(1){
        
        if(killCommand == 1){
            gettimeofday(&end, NULL);
            long time = end.tv_sec-start.tv_sec;
            printf("Ran for %ld seconds\n", time);
            exit(1);
        }
        else if(printStr == 1){
            printStr = 0;
            printf("no\n");
        }
    }
}

void sigint_handler(int sigal){
    printStr = 1;
    signal(SIGINT, sigint_handler);
}

void sigalrm_handler(int sigal){
    killCommand = 1;
}


/*
 * help() - Print a help message.
 */
void help(char *progname) {
    printf("Usage: %s\n", progname);
    printf("Swallow SIGINT, exit on SIGALARM\n");
}

/*
 * main() - The main routine parses arguments and dispatches to the
 *          task-specific code.
 */
int main(int argc, char **argv) {
    /* for getopt */
    long opt;

    /* run a student name check */
    check_student(argv[0]);

    /* parse the command-line options.  For this program, we only support  */
    /* the parameterless 'h' option, for getting help on program usage. */
    while ((opt = getopt(argc, argv, "h")) != -1) {
        switch(opt) {
          case 'h': help(argv[0]); break;
        }
    }

    /* no need to look at arguments before calling the code */
    alive();
}
