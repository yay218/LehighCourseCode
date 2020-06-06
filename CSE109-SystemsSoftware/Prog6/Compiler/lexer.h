/* CSE 109
   Yang Yi
   yay218
   Program Description: This program develops a lexical analyzer,
   which will act as a tokenizer for the compiler we are developing in
   class.
   Program #4
*/

//This is the header file lexer.h.
//This is the interface for the class Lexer.

#ifndef LEXER_H
#define LEXER_H

#include <string>
#include <iostream>
#include "token.h"

using std::string;
using std::istream;

class Lexer
{
 private:
  istream& input; //Member variable for reading the input
  char currentToken; //Read the current token
  int lineNumber; //Keep track of line number
  int position; //Keep track of position
  
 public:
  Lexer(istream& input); //A constructor that takes an istream& as a parameter
  char nextChar();
  //Reads the next character and returns it.
  //When there is #, keep reading until another # or end of file is reached.
  //If nextChar() reaches the end of file, then have it return a $.
  //Keep track of the current line number and the position on the line.
  
  Token nextToken();
  //Returns an object of type Token with the next token in the input
  //stream.
  
}; //Lexer

#endif //LEXER.H
