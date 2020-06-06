/*
  CSE 109
  Yang Yi
  yay218
  Program Description: This is a shell program that prompt the user to
  enter the commands and execute those commands. 
  Program #2
*/

#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>


char ** getopts(char cmd[]);

int main(int argc, char *argv[])
{
  char name[100];
  
  //get the host name of the computer
  gethostname(name, sizeof(name));

  char input[100];
  printf("%s>", name);

  //Ask the user to enter command and read the line user types
  //until the user enter exit
  while (fgets(input, 100, stdin)) {
    
    //chop off the newline char at the end of the line
    if(input[strlen(input)-1] == '\n') {
      input[strlen(input)-1] = '\0';
    }

    //if the user doesn't enter exit, execute the getopts function
    if (strcmp(input, "exit") != 0) {
      argv = getopts(input);

      if (fork() == 0) {
	execvp(argv[0], argv);
      }
      else {
	wait(NULL);
      }

      printf("%s>", name);
      continue;
    }
    else { //if the user enter exit, then exit the program
      return 0;
    }
  }

  return 0;
}
