/* CSE 109
   Yang Yi
   yay218
   Program Description: This program develops a lexical analyzer,
   which will act as a tokenizer for the compiler we are developing in
   class.
   Program #4
*/

//This is the implementation file lexer.cc
//This is the implementation of the class Lexer
#include <string>
#include <iostream>
#include "lexer.h"
#include "token.h"

using std::string;
using std::istream;
using namespace std;

Lexer::Lexer(istream& in):
  input(in), currentToken(' '), lineNumber(1), position(-1)
{
  /*Body intentionally empty*/
}

char Lexer::nextChar()
{
  if(!input.eof()) {
    char token = input.get();
    position++;
    //if there is #, keep reading until the end of file is reached
    if(token == '#') {
      while(token != '\n' && !input.eof()) {
	token = input.get();
	position++;
      }
    }
    if(input.eof()) {
      return ' ';
    }
    else {
      return token;
    }
  }
  else {
    return '$';
  }  
}

Token Lexer::nextToken()
{
  //check for group2 and return values
  while(true) {
    switch(currentToken) {
    case '+': //PLUS
      currentToken = nextChar();
      return Token(3, "+", lineNumber, position);
      break;
      
    case '-': //MINUS
      currentToken = nextChar();
      return Token(4, "-", lineNumber, position);
      break;
      
    case '*': //TIMES
      currentToken = nextChar();
      return Token(5, "*", lineNumber, position);
      break;
      
    case '/': //DIVIDE
      currentToken = nextChar();
      return Token(6, "/", lineNumber, position);
      break;
      
    case '%': //REM
      currentToken = nextChar();
      return Token(7, "%", lineNumber, position);
      break;
      
    case '(': //LPAREN
      currentToken = nextChar();
      return Token(9, "(", lineNumber, position);
      break;
      
    case ')': //RPAREN
      currentToken = nextChar();
      return Token(10, ")", lineNumber, position);
      break;
      
    case ',': //COMMA
      currentToken = nextChar();
      return Token(11, ",", lineNumber, position);
      break;
      
    case '=': //get one more token to handle EQ and ASSIGN
      //if EQ, return the begin position, which is position-1
      currentToken = nextChar();
      if(currentToken == '=') {
	currentToken = nextChar();
	return Token(12, "==", lineNumber, position-1);
      }
      return Token(8, "=", lineNumber, position);
      break;
      
    case '<': //get one more token to handle LT and LE
      //if LE, return the begin position, which is position-1 
      currentToken = nextChar();
      if(currentToken == '=') {
	currentToken = nextChar();
	return Token(14, "<=", lineNumber, position-1);
      }
      return Token(13, "<", lineNumber, position);
      break;
      
    case '>': //get one more token to handle GT and GE
      //if GE, return the begin position, which is position-1
      currentToken = nextChar();
      if(currentToken == '=') {
	currentToken = nextChar();
	return Token(16, ">=", lineNumber, position-1);
      }
      return Token(15, ">",lineNumber, position);
      break;
      
    case '!': //get one more token to handle NE and NOT
      //if NE, return the begin position, which is position-1  
      //if NOT, return ERROR
      currentToken = nextChar();
      if(currentToken == '=') {
	currentToken = nextChar();
	return Token(17, "!=", lineNumber, position-1);
      }
      return Token(31, "!", lineNumber, position);
      break;
	
    case '$': //ENDOFFILE
      return Token(30, "$", lineNumber, position);
      break;

    case ' ': //WHITESPACE
      currentToken = nextChar();
      break;
      
    case '\n':
      //if a new line begin, clean position and imcrement line number
      currentToken = nextChar();
      lineNumber++;
      position = 0;
      break;
	
    default: //DEFAULT case to handle INTLIT and IDENT
      if(isdigit(currentToken)) { //if begin with a digit
	//if begin with 0, two possibilities: 0 and hexadecimal
	if(currentToken == '0') {
	  currentToken = nextChar();
	  int beginPosition = position;
	  if(currentToken == 'x') { //hexadecimal situation
	    string value = "0x";
	    currentToken = nextChar();
	    if(isxdigit(currentToken)) {
	      while(isxdigit(currentToken)) {
		value += currentToken;
		currentToken = nextChar();
	      }
	      return Token(1, value, lineNumber, beginPosition);
	    }
	    else { //NOT hexadecimal, return ERROR
	      value += currentToken;
	      return Token(31, value, lineNumber, beginPosition);
	    }
	  }
	  else { //just integer 0
	    return Token(1, 0, lineNumber, beginPosition);
	  }
	}
	else { //begin with digit but not 0, in other words:1-9
	  string value;
	  value += currentToken;
	  currentToken = nextChar();
	  int beginPosition = position;
	  while(isdigit(currentToken)) {
	    value += currentToken;
	    currentToken = nextChar();
	  }
	  return Token(1, value, lineNumber, beginPosition);
	}
      }
      else if(isalpha(currentToken)) { //begin with a-z or A-Z
	string value = "";
	value =+ currentToken;
	currentToken = nextChar();
	int beginPosition = position;
	
	//check situation [a-zA-Z0-9_]
	while(isalnum(currentToken) || currentToken == '_') {
	  value += currentToken;
	  currentToken = nextChar();
	}
	
	//let words to be lower case and return their value
	//if words are in the third group, return assigned value
	//if not, return 1, since they're INTLIT
	for(unsigned int i = 0; i < value.length(); i++) {
	  value[i]= tolower(value[i]);
	}
	if(value == "set") {
	  return Token(18, value, lineNumber, beginPosition);
	}
	else if(value == "print") {
	  return Token(19, value, lineNumber, beginPosition);
	}
	else if(value == "while") {
	  return Token(20, value, lineNumber, beginPosition);
	}
	else if(value == "do") {
	  return Token(21, value, lineNumber, beginPosition);
	}
	else if(value == "end") {
	  return Token(22, value, lineNumber, beginPosition);
	}
        else if(value == "and") {
          return Token(23, value, lineNumber, beginPosition);
        }
	else if(value == "or") {
	  return Token(24, value, lineNumber, beginPosition);
        }
	else if(value == "if") {
	  return Token(25, value, lineNumber, beginPosition);
	}
	else if(value == "then") {
	  return Token(26, value, lineNumber, beginPosition);
	}
	else if(value == "else") {
	  return Token(27, value, lineNumber, beginPosition);
	}
	else if(value == "endif") {
	  return Token(28, value, lineNumber, beginPosition);
	}
	else if(value == "program") {
	  return Token(29, value, lineNumber, beginPosition);
        }
	else {
	  return Token(2, value, lineNumber, beginPosition);
	}
      }
      else { //if other letter, return ERROR
	string value = "";
	value += currentToken;
	currentToken = nextChar();
	int beginPosition = position;
        return Token(31, value, lineNumber, beginPosition);
      }
    }
  }
}
