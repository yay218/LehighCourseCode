/* CSE 109
   Yang Yi
   yay218
   Program Description: This program develops a lexical analyzer,
   which will act as a tokenizer for the compiler we are developing in
   class.
   Program #5
*/
#ifndef PARSER_H
#define PARSER_H

#include "token.h"
#include "lexer.h"
#include <iostream>
#include <string>
#include <cstring>
#include <stdlib.h>
#include <sstream>

using namespace std;

class Parser {
  
public:  
  class TreeNode {
  };

  
private:
  Lexer lexer;
  Token token;
  ostream& out;


  void error(string message);
  void check(int tokenType, string message);
  
 public:
  TreeNode *program();
  TreeNode* compoundStatement();
  TreeNode* statement();
  TreeNode* setStatement();
  TreeNode* printStatement();
  TreeNode* whileStatement();
  TreeNode* ifStatement();
  TreeNode* logicalExpression();
  TreeNode* relationalExpression();
  TreeNode* expression();
  TreeNode* term();
  TreeNode* factor();

  
  Parser(Lexer& lexer, ostream& out);
  ~Parser(); 


};

#endif
