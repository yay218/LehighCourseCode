/* CSE 109
   Yang Yi
   yay218
   Program Description: This program develops a lexical analyzer,
   which will act as a tokenizer for the compiler we are developing in
   class.
   Program #4
*/

//This is the header file token.h
//This is the interface for the class Token
#ifndef TOKEN_H
#define TOKEN_H

#include <string>

using std::string;

class Token
{
 private:
  int tokenType; //An integer to hold the token type
  string lex; //A string to hold the lexeme
  int lineNumber; //An integer to hold the input file line number
  int position; //An integer to hold the character position on the line

 public:
  Token();
  Token(int tokenType, string lexeme, int lineNumber, int position);
  //The constructor
  
  ~Token(); //Destructor
  
  int type(); //Return token type
  string lexeme(); //Return the lexeme
  int line(); //Return the input file line number
  int pos(); //Return thr character position on the line

  //Group1 are tokens where the lexeme will change with each token
  //that is read
  static const int INTLIT = 1;
  static const int IDENT = 2;

  //Group2 are the usual one or two character special character symbols: +,
  //-, ==, <=, etc.
  static const int PLUS = 3;
  static const int MINUS = 4;
  static const int TIMES = 5;
  static const int DIVIDE = 6;
  static const int REM = 7;
  static const int ASSIGN = 8;
  static const int LPAREN = 9;
  static const int RPAREN = 10;
  static const int COMMA = 11;
  static const int EQ = 12;
  static const int LT = 13;
  static const int LE = 14;
  static const int GT = 15;
  static const int GE = 16;
  static const int NE = 17;

  //Group3 are the keywords of the language: while, if, etc.
  static const int SET = 18;
  static const int PRINT = 19;
  static const int WHILE = 20;
  static const int DO = 21;
  static const int END = 22;
  static const int AND = 23;
  static const int OR = 24;
  static const int IF = 25;
  static const int THEN = 26;
  static const int ELSE = 27;
  static const int ENDIF = 28;
  static const int PROGRAM = 29;

  //ENDOFFILE stands for end-of-file.
  //ERROR is the token type returned when no other token is recognized.
  static const int ENDOFFILE = 30;
  static const int ERROR = 31;
}; //Token

#endif
