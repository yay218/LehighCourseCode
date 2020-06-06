/* CSE 109
   Yang Yi
   yay218
   Program Description: This program develops a lexical analyzer,
   which will act as a tokenizer for the compiler we are developing in
   class.
   Program #5
*/
#include "parser.h"
#include <cstring>


Parser::Parser(Lexer& lexerx, ostream& outx): lexer(lexerx), out(outx) {
  token = lexer.nextToken();
}

Parser::~Parser() {
}

void Parser::error(string message) {
  cerr << message << " Found " << token.lexeme() << " at line " << token.line() << " position " << token.pos() << endl;
  exit(1);
}

void Parser::check(int tokenType, string message) {
  if (token.type() != tokenType)
    error(message);
}


Parser::TreeNode* Parser::factor() {
  TreeNode* node = NULL;
  if(token.type() == Token::LPAREN){
    token = lexer.nextToken();
    node = expression();
    if(token.type() == Token::RPAREN){
      token = lexer.nextToken();
    }
    else {
      error("Missing RPAREN");
    }
  }
  else if(token.type() == Token::INTLIT){
    node = NULL;
    token = lexer.nextToken();
  }
  else if(token.type() == Token::IDENT){
    node = NULL;
    token = lexer.nextToken();
  }
  else{
    error("Error occurred in factor");
  }
  return node;
}

Parser::TreeNode* Parser::term() {
  TreeNode* termNode = factor();
  TreeNode* factorNode = NULL;
  while (token.type() == Token::TIMES || token.type() == Token::DIVIDE) {
    token = lexer.nextToken();
    factorNode = factor();
    switch (token.type()) {
    case Token::TIMES:
      termNode = NULL;
      break;
    case Token::DIVIDE:
      termNode = NULL;
      break;
    }
  }
  return termNode;
}

Parser::TreeNode* Parser::expression() {
  TreeNode* expressionNode = term();
  TreeNode* termNode;
  while (token.type() == Token::PLUS || token.type() == Token::MINUS) {
    token = lexer.nextToken();
    termNode = term();
    switch (token.type()) {
    case Token::PLUS:
      expressionNode = NULL;
      break;
    case Token::MINUS:
      expressionNode = NULL;
      break;
    }
  }
  return expressionNode;
}

Parser::TreeNode* Parser::relationalExpression() {
  TreeNode* node = expression();
  switch (token.type()) {
  case Token::EQ:
    token = lexer.nextToken();
    node = NULL;
    break;
  case Token::LT:
    token = lexer.nextToken();
    node = NULL;
    break;
  case Token::LE:
    token = lexer.nextToken();
    node = NULL;
    break;
  case Token::GT:
    token = lexer.nextToken();
    node = NULL;
  case Token::GE:
    token = lexer.nextToken();
    node = NULL;
    break;
  case Token::NE:
    token = lexer.nextToken();
    node = NULL;
    break;
  default:
    error("Unrecognized relation");
    break;
  }
  return node;
}

Parser::TreeNode* Parser::logicalExpression() {
  TreeNode* logicalNode = relationalExpression();
  TreeNode* rationalNode = NULL;
  while(token.type() == Token::AND || token.type() == Token::OR){
    if(token.type() == Token::AND){
      token = lexer.nextToken();
      rationalNode = relationalExpression();
      logicalNode = NULL;
    }
    else{
      token = lexer.nextToken();
      rationalNode = relationalExpression();
      logicalNode = NULL;
    }
  }
  return logicalNode;
}

Parser::TreeNode* Parser::setStatement() {
  TreeNode* setstatementNode = NULL;
  TreeNode* expressionNode = NULL;
  if(token.type() == Token::SET){
    token = lexer.nextToken();
    if(token.type() == Token::IDENT){
      setstatementNode = NULL;
      token = lexer.nextToken();
      if(token.type() == Token::ASSIGN){
	token = lexer.nextToken();
	expressionNode = expression();
	setstatementNode = NULL;
      }
      else{
	error("Missing assignment");
      }
    }
    else{
      error("Missing IDENT");
    }
  }
  else{
    error("Error in setStatement");
  }

  return setstatementNode;
}

Parser::TreeNode* Parser::printStatement() {
  TreeNode* printNode = NULL;
  if(token.type() == Token::PRINT){
    token = lexer.nextToken();
    while(token.type() == Token::COMMA){
      token = lexer.nextToken();
      printNode = NULL;
    }
  }
  else{
    error("Error in printStatement");
  }
  return printNode;
}

Parser::TreeNode* Parser::whileStatement() {
  TreeNode* whileNode = NULL;
  if(token.type() == Token::WHILE){
    token = lexer.nextToken();
    whileNode = logicalExpression();
    if(token.type() == Token::DO){
      token = lexer.nextToken();
      if(token.type() == Token::END){
	token = lexer.nextToken();
      }
      else{
	error("Missing END");
      }
    }
    else{
      error("Missing DO");
    }
  }
  else{
    error("Missing WHILE");
  }
  return whileNode;
}

Parser::TreeNode* Parser::ifStatement() {
  TreeNode* ifNode =  NULL;
  TreeNode* compoundNode = NULL;
  if(token.type() == Token::IF){
    token = lexer.nextToken();
    ifNode = logicalExpression();
    if(token.type() == Token::THEN){
      token = lexer.nextToken();
      compoundNode = compoundStatement();
      if(token.type() == Token::ENDIF){
	ifNode = NULL;
      }
      else if(token.type() == Token::ELSE){
	token = lexer.nextToken();
	ifNode = NULL;
      }
      if(token.type() == Token::ENDIF){
	token = lexer.nextToken();
      }
      else{
	error("Missing ENDIF");
      }
    }
    else{
      error("Missing THEN");
    }
  }
  else{
    error("Missing IF");
  }
  return ifNode;
}

Parser::TreeNode* Parser::statement() {
  TreeNode* statementNode = NULL;
  switch (token.type()) {
  case Token::SET:
    statementNode = setStatement();
    break;
  case Token::PRINT:
    statementNode = printStatement();
    break;
  case Token::IF:
    statementNode = ifStatement();
    break;
  case Token::WHILE:
    statementNode = whileStatement();
    break;
  default:
    error("Error in statement");
    break;
  }
  return statementNode;
}

Parser::TreeNode* Parser::compoundStatement() {
  TreeNode* compoundstatementNode = statement();
  TreeNode* statementNode;
  while(token.type() == Token::SET || token.type() == Token::PRINT || token.type() ==  Token::WHILE || token.type() == Token::IF){
    statementNode = statement();
    compoundstatementNode = NULL;
  }
  return compoundstatementNode;
}

Parser::TreeNode* Parser::program() {
  TreeNode* programNode = NULL;
  if(token.type() == Token::PROGRAM){
    token = lexer.nextToken();
    if(token.type() == Token::IDENT){
      token = lexer.nextToken();
      programNode = compoundStatement();
      if(token.type() == Token::END){
	token = lexer.nextToken();
	if(token.type() == Token::PROGRAM){
	}
	else{
	  error("Missing 'PROGRAM'");
	}
      }
      else{
	error("Missing 'END'");
      }
    }
    else{
      error("Error occurred in compoundstatement");
    }
  }
  else{
    error("Missing 'PROGRAM'");
  }
  cout << "Program Accepted";
  return programNode;
}

