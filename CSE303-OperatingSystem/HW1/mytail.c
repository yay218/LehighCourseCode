#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>
#include "support.h"

/*
 * tail_file() - display the last numlines lines of a file or stdin
 */
void tail_file(char *filename, int numlines) {

    FILE *file;
    if(filename == NULL) {
        file = stdin;
    }
    else {
        file = fopen(filename, "r");

    }
    int count = 0;
  
    fseek(file, 0, SEEK_END);
    int size = ftell(file);
    char *buf = malloc((size + 1) * sizeof(char));
    
    while (size) {
        fseek(file, --size, SEEK_SET);
        if (fgetc(file) == '\n'){
            count++;
            if (count == numlines) {
                break;
            }
        }
    }

    while (fgets(buf, sizeof(buf), file)) {
        fputs(buf, stdout);
    }
    //printf("\n");
    fclose(file);
    free(buf);
}

/*
 * help() - Print a help message.
 */
void help(char *progname) {
    printf("Usage: %s [FILE]\n", progname);
    printf("Print the last 10 lines  of FILE to STDOUT\n");
    printf("With no FILE, read from STDIN\n");
    printf("  -nK    print the last K lines instead of the last 10\n");
}

/*
 * main() - The main routine parses arguments and dispatches to the
 *          task-specific code.
 */
int main(int argc, char **argv) {
    /* for getopt */
    long opt;
    int numlines = 10; /* default number of lines */
    char *filename;

    /* run a student name check */
    check_student(argv[0]);

    /* parse the command-line options.  For this program, we only support  */
    /* the parameterless 'h' option, for getting help on program usage, */
    /* and the parametered 'n' option, for getting the number of lines. */
    /* TODO: parse the arguments correctly */
    while ((opt = getopt(argc, argv, "hn:")) != -1) {
        switch(opt) {
          case 'h': help(argv[0]); break;
          case 'n': numlines = atoi(optarg); break;
        }
    }

    /* TODO: fix this invocation */
    if(optind < argc) {
        filename = argv[optind];
    }
    else {
        filename = NULL;
    }
    tail_file(filename, numlines);
}
