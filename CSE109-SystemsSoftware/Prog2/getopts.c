/*
  CSE 109
  Yang Yi
  yay218
  Program Description: This is a shell program that prompt the user to
  enter the commands and execute those commands.
  Program #2
*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

char ** getopts(char cmd[]) {
  char input[100];
  char *token;
  int numOfTok = 0;
  strcpy(input, cmd);

  //Use strtok to extract each blank separated token from the string
  token = strtok(input, " ");

  //Count the number of tokens
  while(token != NULL) {
    numOfTok++;
    token=strtok(NULL, " ");
  }
  
  //Dynamically allocate the memory for array using malloc
  char **array = (char**)malloc((numOfTok+1) * sizeof(char*));
  int i = 0;
  
  //Copying the token strings into the array of strings
  //Again use strtok to extract each blank separated token from the string 
  strcpy(input, cmd);
  token = strtok(input,  " ");

  //Use malloc to dynamically allocate the memory for each string
  while (token != NULL) {
    array[i] = (char*)malloc(strlen(token) * sizeof(char));
    strcpy(array[i], token);
    token = strtok(NULL, " ");
    i++;
  }

  //Set the array element after the last argument to 0
  array[numOfTok] = 0;

  return array;

}
