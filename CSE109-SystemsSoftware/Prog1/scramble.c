/*
CSE 109
Yang Yi
yay218
Program Description: This is a program that takes a string as input and prints the scrambled string while ignoring the punctuation and  keeping the first and last letters unchanged.
Program #1
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<stdbool.h>
#include <time.h>


char* scramble(char *str1);
//Precondition: The string without punctuation
//Returns a string with scrambled each word, but the first and last letters remain in the same positions



int main() {
  srand((unsigned)time(NULL));

  char *input;
  long input_file_size;
  FILE *input_file = fopen("MyFile", "rb");
  fseek(input_file, 0, SEEK_END);
  input_file_size = ftell(input_file);
  rewind(input_file);
  input = malloc(input_file_size * (sizeof(char)));
  fread(input, sizeof(char), input_file_size, input_file);

  int inputLength = strlen(input);
  char word[1000] = "";
  char punctuation;
  int wordLength = 0;
  int i;
  int j;
  int k;
  
  
  for (i = 0; i < inputLength; i++)
    {
      bool check = false;
    
      for (j = 97; j <= 122; j++)
  	{
  	  if (input[i] == j)
  	    {
  	      word[i-wordLength] = input[i];
	      check = true;
	    }
  	}
      for (k = 65; k <= 90; k++)
  	{
  	  if (input[i] == k)
  	    {
  	      word[i-wordLength] = input[i];
	      check = true;
	    }
	  
  	}
      
      if (check == false)
	{
	  punctuation = input[i];
	  wordLength = wordLength + strlen(word)+1;
	  printf("%s", scramble(word));
	  printf("%c", punctuation);
	  memset(word, 0, 200);
	}
    }
  printf("%s\n", scramble(word));
  return 0;
}


char* scramble(char* str)
{
  int length = strlen(str);
  int ran;
  int i;

  if (length > 3)
    {
      for (i = 1; i < length - 1; i++)
	{
	  ran = (rand() % (length - 2)) + 1;
	  char tmp = str[i];
	  str[i] = str[ran];
	  str[ran] = tmp;
	}
    }
 
  return str;
  
}

