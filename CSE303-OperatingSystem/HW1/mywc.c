#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>
#include "support.h"

/*
 * wc_file() - count characters, words, and lines
 */
void wc_file(char *filename) {
    int character = 0, word = 0, line = 0;
    char current;
    FILE *file;
    file = fopen(filename, "r");
    
    if(file == NULL) {
        printf("Error opening file");
    }
    else {
        while(1) {
            current = getc(file);
            if(current == EOF) {
                word++;
                break;
            }
            else if ((current >= 97 && current <= 122)||(current >= 65 && current <= 90)||(current >= 48 && current <= 57)) {
                character++;
            }
            else if(current == ' ' || current == '\t') {
                word++;
            }
            else if(current == '\n') {
                word++;
                line++;
            }
        }
    }
    printf("%d %d %d %s\n", line, word, character, filename);
}

/*
 * help() - Print a help message.
 */
void help(char *progname) {
    printf("Usage: %s [FILE]\n", progname);
    printf("Print newline, word, and byte counts for FILE\n");
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

    /* error if we don't have a file, else call wc_file */
    if (argc != 2) {
        printf("Error: no file given\n");
        exit(1);
    }
    wc_file(argv[1]);
}
