#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "support.h"


struct link {
    int value;
    struct link *next;
};

struct link *head;
struct link *pointer;
struct link *last;
struct link *current;


void insert(int value, int integer) {
    if(head != NULL) {
        last = NULL;
        pointer = head;
        while(pointer->next != NULL) {
            if(pointer->value - integer > 0) {
                break;
            }
            else {
                last = pointer;
                pointer = pointer->next;
            }
        }
        
        if(pointer->next == NULL) {
            pointer->next = current;
        }
        else if(last == NULL) {
            current->next = pointer;
            head = current;
        }
        else {
            current->next = pointer;
            last->next = current;
        }
    }
    else {
        head = current; 
    }
}

void delete(int value, int integer) {
    pointer = head;
    last = NULL;
    while(pointer != NULL) {
        if(pointer->value == integer) {
            if(pointer == head) {
                head = pointer->next;
            }
            else if(pointer->next == NULL) {
                last->next = NULL;
            }
            else {
                last->next = pointer->next;
            }
            free(pointer);
            break;
        }
        last = pointer;
        pointer = pointer->next;
    }
}

void print() {
    if(head != NULL) {
        pointer = head;
        while(pointer->next != NULL) {
            if(pointer->value != 0) {
                printf("%d-->", pointer->value);
            }
            pointer = pointer->next;
        }
        printf("%d\n", pointer->value);
    }
}

/*
 * list_task() - read from stdin, determine the appropriate behavior, and
 *               perform it.
 */
void list_task(void) {
    /* TODO: Complete this function */
    char* command;
    char* num;
    //int integer;
    char line[1024];

    while(fgets(line, sizeof(line), stdin) != NULL){
        command = strtok(line, " ");
        num = strtok(NULL, " ");

/*
        char num[1024];
        strcpy(num , strtok(NULL, " "));
        integer = 0;
        int len = strlen(num);
        int i = 0;
        for(i = 0; i < len - 1; i++){
            integer = integer * 10 + (num[i] - '0');
        }
        */

        //integer = atoi(num);

        if(strcmp(command, "i") == 0){
            current = malloc(sizeof(struct link));
            current->value = atoi(num);
            current->next = NULL;
            insert(current->value, atoi(num));
        }
        else if(strcmp(command, "r") == 0) {
            current = malloc(sizeof(struct link));
            current->value = atoi(num);
            current->next = NULL;
            delete(current->value, atoi(num));
        }
        else if(strcmp(command, "p") == 0){
            print();
        }
        else if(strcmp(command, "x") == 0){
            pointer = head;
            while (1){
                if(pointer != NULL) {
                    free(pointer);
                    pointer = pointer->next;
                }
                else {
                    free(pointer);
                    break;
                }
            }            
            exit(0);
        }
        else{
            printf("Command is not valid\n");
            exit(-1);
        }
    }
    pointer = head;
    while (1){
        if(pointer != NULL) {
            free(pointer);
            pointer = pointer->next;
        }
        else {
            free(pointer);
            break;
        }
    }    
}


/*
 * help() - Print a help message.
 */
void help(char *progname) {
    printf("Usage: %s\n", progname);
    printf("Run an interactive list manipulation program\n");
    printf("Commands: [i]insert, [r]emove, [p]rint, e[x]it\n");
    printf("  (Commands require an integer argument)\n");
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

    /*
     * parse the command-line options.  For this program, we only support the
     * parameterless 'h' option, for getting help on program usage.
     */
    while ((opt = getopt(argc, argv, "h")) != -1) {
        switch(opt) {
          case 'h': help(argv[0]); break;
        }
    }

    /* run the list program */
    list_task();
}
