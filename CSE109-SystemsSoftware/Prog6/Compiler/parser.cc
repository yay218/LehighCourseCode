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

const string Parser::ops[] = {"ADD", "SUB", "MULT", "DIV", "AND", "OR", "ISEQ", "ISGE", "ISGT", "ISLE", "ISLT", "ISNE", "JUMP", "JUMPF", "JUMPT",  "LIT", "VAR", "STORE", "LABEL", "PRINT", "SEQ", "PRINTLN"};

Parser::Parser(Lexer& lexerx, ostream& outx): lexer(lexerx), out(outx), lindex(1), tindex(1) {
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
  TreeNode* factorNode = NULL;
  if(token.type() == Token::LPAREN){
    token = lexer.nextToken();
    factorNode = expression();
    if(token.type() == Token::RPAREN){
      token = lexer.nextToken();
    }
    else {
      error("Missing RPAREN");
    }
  }
  else if(token.type() == Token::INTLIT){
    factorNode = new TreeNode(LIT, token.lexeme());
    token = lexer.nextToken();
  }
  else if(token.type() == Token::IDENT){
    factorNode = new TreeNode(VAR, token.lexeme());
    token = lexer.nextToken();
  }
  else{
    error("Error occurred in factor");
  }
  return factorNode;
}

Parser::TreeNode* Parser::term() {
  TreeNode* termNode = factor();
  TreeNode* factorNode;
  while (token.type() == Token::TIMES || token.type() == Token::DIVIDE) {
    token = lexer.nextToken();
    factorNode = factor();
    switch (token.type()) {
    case Token::TIMES:
      termNode = new TreeNode(MULT, termNode, factorNode);
      break;
    case Token::DIVIDE:
      termNode = new TreeNode(DIV, termNode, factorNode);;
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
      expressionNode = new TreeNode(ADD, expressionNode, termNode);
      break;
    case Token::MINUS:
      expressionNode = new TreeNode(SUB, expressionNode, termNode);
      break;
    }
  }
  return expressionNode;
}

Parser::TreeNode* Parser::relationalExpression() {
  TreeNode* reNode = expression();
  switch (token.type()) {
  case Token::EQ:
    token = lexer.nextToken();
    reNode = new TreeNode(ISEQ, reNode, expression());
    break;
  case Token::LT:
    token = lexer.nextToken();
    reNode = new TreeNode(ISLT, reNode, expression());
    break;
  case Token::LE:
    token = lexer.nextToken();
    reNode = new TreeNode(ISLE, reNode, expression());
    break;
  case Token::GT:
    token = lexer.nextToken();
    reNode = new TreeNode(ISGT, reNode, expression());
  case Token::GE:
    token = lexer.nextToken();
    reNode = new TreeNode(ISGE, reNode, expression());
    break;
  case Token::NE:
    token = lexer.nextToken();
    reNode = new TreeNode(ISNE, reNode, expression());
    break;
  default:
    error("Error in relational expression");
    break;
  }
  return reNode;
}

Parser::TreeNode* Parser::logicalExpression() {
  TreeNode* leNode = relationalExpression();
  TreeNode* reNode = NULL;
  while(token.type() == Token::AND || token.type() == Token::OR){
    if(token.type() == Token::AND){
      token = lexer.nextToken();
      reNode = relationalExpression();
      leNode = new TreeNode(AND, leNode, reNode);
    }
    else{
      token = lexer.nextToken();
      reNode = relationalExpression();
      leNode = new TreeNode(OR, leNode, reNode);
    }
  }
  return leNode;
}

Parser::TreeNode* Parser::setStatement() {
  TreeNode* ssNode = NULL;
  TreeNode* expressionNode = NULL;
  if(token.type() == Token::SET){
    token = lexer.nextToken();
    if(token.type() == Token::IDENT){
      ssNode = new TreeNode(STORE, token.lexeme());
      token = lexer.nextToken();
      if(token.type() == Token::ASSIGN){
	token = lexer.nextToken();
	expressionNode = expression();
	ssNode = new TreeNode(SEQ, expressionNode, ssNode);
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

  return ssNode;
}

Parser::TreeNode* Parser::printStatement() {
  TreeNode* root = NULL;
  TreeNode* printNode = new TreeNode(PRINT);
  TreeNode* psNode = NULL;
  TreeNode* printlnNode = new TreeNode(PRINTLN);
  if(token.type() == Token::PRINT){
    token = lexer.nextToken();
    root = new TreeNode(SEQ, expression(), printNode);
    while(token.type() == Token::COMMA){
      token = lexer.nextToken();
      psNode = new TreeNode(SEQ, expression(), printNode);
      root = new TreeNode(SEQ, root, psNode);
    }
    root = new TreeNode(SEQ, root, printlnNode);
  }
  else{
    error("Error in printStatement");
  }
  return root;
  
}

Parser::TreeNode* Parser::whileStatement() {
  TreeNode* wsNode = NULL;
  string label1 = makeLabel();
  string label2 = makeLabel();
  TreeNode* jumpNode1 = new TreeNode(JUMP, label1);
  TreeNode* jumpNode2 = new TreeNode(JUMPF, label2);
  TreeNode* labelNode1 = new TreeNode(LABEL, label1+":");
  TreeNode* labelNode2 = new TreeNode(LABEL, label2+":");
  if(token.type() == Token::WHILE){
    token = lexer.nextToken();
    wsNode = logicalExpression();
    if(token.type() == Token::DO){
      token = lexer.nextToken();
      TreeNode* root = new TreeNode(SEQ, jumpNode1, labelNode2);
      root = new TreeNode(SEQ, compoundStatement(), root);
      root = new TreeNode(SEQ, jumpNode2, root);
      wsNode = new TreeNode(SEQ, wsNode, root);
      wsNode = new TreeNode(SEQ, labelNode1, wsNode);
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
  return wsNode;
}

Parser::TreeNode* Parser::ifStatement() {
  TreeNode* isNode =  NULL;
  TreeNode* csNode = NULL;
  string label = makeLabel();
  TreeNode* jumpfNode = new TreeNode(JUMPF, label);
  TreeNode* labelNode = new TreeNode(LABEL, label + ":");
  if(token.type() == Token::IF){
    token = lexer.nextToken();
    isNode = logicalExpression();
    if(token.type() == Token::THEN){
      token = lexer.nextToken();
      csNode = compoundStatement();
      if(token.type() == Token::ENDIF){
	csNode = new TreeNode(SEQ, csNode, labelNode);
	jumpfNode = new TreeNode(SEQ, jumpfNode, csNode);
	isNode = new TreeNode(SEQ, isNode, jumpfNode);
      }
      else if(token.type() == Token::ELSE){
	token = lexer.nextToken();
	string label2 = makeLabel();
	TreeNode* label2Node = new TreeNode(LABEL, label2 + ":");
	TreeNode* cs2Node = new TreeNode(SEQ, compoundStatement(), label2Node);
	labelNode = new TreeNode(SEQ, labelNode, cs2Node);
	TreeNode* jumpf2Node = new TreeNode(JUMPF, label2);
	jumpf2Node = new TreeNode(SEQ, jumpf2Node, labelNode);
	csNode = new TreeNode(SEQ, csNode, jumpf2Node);
	csNode = new TreeNode(SEQ, jumpfNode, csNode);
	isNode = new TreeNode(SEQ, isNode, csNode);
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
  return isNode;
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
  TreeNode* csNode = statement();
  TreeNode* statementNode;
  while(token.type() == Token::SET || token.type() == Token::PRINT || token.type() ==  Token::WHILE || token.type() == Token::IF){
    statementNode = statement();
    csNode = new TreeNode(SEQ, csNode, statementNode);
  }
  return csNode;
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
  cout << "Program Accepted" << endl;
  return programNode;
}

