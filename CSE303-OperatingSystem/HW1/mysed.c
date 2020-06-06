#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "support.h"

/*
 * sed_file() - modify lines of filename based on pattern, and print result
 *              to STDOUT
 */
void sed_file(char *filename, char *pattern) {
    /* TODO: Complete this function */
    FILE *file;
    file = fopen(filename, "r");
    char *searchstr, *replacestr;
    char line[1024] = "";

    if(file == NULL) {
        perror("Error opening file");
        exit(-1);
    }

    strtok(pattern, "/");
    searchstr = strtok(NULL, "/");
    replacestr = strtok(NULL, "/");
    //printf("%s %s %s\n", token, searchstr, replacestr);
    if(strcmp(replacestr,"g") == 0){
        replacestr = "";
    }


    while (fgets(line, sizeof(line), file) != NULL) {
        if (strstr(line, searchstr) != NULL) {
            char *before = line;
            while(strstr(before, searchstr)) {
                while(before != strstr(before, searchstr)) {
                    printf("%c", before[0]);
                    before++;
                }
                printf("%s", replacestr);
                int i = 0;
                while(i < strlen(searchstr)) {
                    before++;
                    i++;
                }
            }
            printf("%s", before);
        }
        else {
            printf("%s", line);
        }
    }
}

/*
 * help() - Print a help message.
 */
void help(char *progname) {
    printf("Usage: %s [FILE]\n", progname);
    printf("Print the contents of FILE to STDOUT\n");
    printf("With no FILE, read standard input\n");
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

    /* TODO: fix this invocation */
    sed_file(optind < argc ? argv[optind+1] : NULL, optind < argc ? argv[optind] : NULL);
}
