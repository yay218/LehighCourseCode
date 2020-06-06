#include <getopt.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "support.h"

char *strdup(const char *string) {
    int length = strlen(string);
    char *duplicate = malloc(length + 1);
    if(duplicate) {
        strcpy(duplicate, string);
    }
    return duplicate;
}

int compare(const void * a, const void * b) {
   return (strcmp(*(char **)a, *(char **)b));
}

/*
 * sort_file() - read a file, sort its lines, and output them.  reverse and
 *               unique parameters are booleans to affect how the lines are
 *               sorted, and which lines are output.
 */
void sort_file(char *filename, int unique, int reverse) {
    /* TODO: Complete this function */
    /* Note: you will probably need to implement some other functions */

    char *array[1024];
    char line[1024] = "";
    int i = 0, j = 0, count = 0;
    FILE *file;
    file = fopen(filename, "r");

    if(file == NULL) {
        perror("Error opening file");
        exit(-1);
    }
    
    while (fgets(line, sizeof(line), file) != NULL) {
        int newline = strcspn(line, "\n");
        line[newline] = '\0';
        int sizeArray = sizeof(array);
        int sizeArrayPointer = sizeof(*array);
        if (i < (sizeArray / sizeArrayPointer)) {
            array[i] = strdup(line);
            i++;
        }
        else {
            break;
        }
    }

    fclose(file);
    qsort(array, i, sizeof(const char *), compare);


    if(reverse == 1) {
        int start = 0, end = i-1;
        char* temp;
        while (start < end) {
            temp = array[start];   
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }

    }

    if(unique == 1) {
        char** array2;
        count = 1;
        for (j = 1; j < i; j++) {
            if(strcmp(array[j],array[j-1])!=0) {
                count++; 
            }
        }
        array2 = (char**)malloc(count * sizeof(char*));
        count =1;
        array2[0] = array[0];
        for (j = 1; j < i; j++) {
            if(strcmp(array[j],array[j-1])!=0) {
                array2[count] = array[j];
                count++; 
            }
        }
        for (j = 0; j < count; j++) {
            printf("%s\n", array2[j]);
        }
        for (j = 0; j < count; j++) {
            free(array2[j]);
        }
    }
    else {
        for (j = 0; j < i; j++) {
            printf("%s\n", array[j]);
        }
    }

    for (j = 0; j < i; j++) {
        free(array[j]);
    }
    
}



/*
 * help() - Print a help message.
 */
void help(char *progname) {
    printf("Usage: %s [OPTIONS] FILE\n", progname);
    printf("Sort the lines of FILE and print them to STDOUT\n");
    printf("  -r    sort in reverse\n");
    printf("  -u    only print unique lines\n");
}

/*
 * main() - The main routine parses arguments and dispatches to the
 *          task-specific code.
 */
int main(int argc, char **argv) {
    /* for getopt */
    long opt;
    int reverse = 0, unique = 0;

    /* ensure the student name is filled out */
    check_student(argv[0]);

    /* parse the command-line options.  They are 'r' for reversing the */
    /* output, and 'u' for only printing unique strings.  'h' is also */
    /* supported. */
    /* TODO: parse the arguments correctly */
    while ((opt = getopt(argc, argv, "hru")) != -1) {
        switch(opt) {
          case 'h': help(argv[0]); break;
          case 'r': reverse = 1; break;
          case 'u': unique = 1; break;
        }
    }
    /* TODO: fix this invocation */
    sort_file(argv[optind], unique, reverse);
}
