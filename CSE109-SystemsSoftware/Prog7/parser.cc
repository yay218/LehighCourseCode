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
  i = 0;
  jmp = 1;
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
    //cout << token.lexeme()<<endl;
    error("Error occurred in factor");
  }
  return factorNode;
}

Parser::TreeNode* Parser::term() {
  TreeNode* termNode = factor();
  TreeNode* factorNode;
  while (token.type() == Token::TIMES || token.type() == Token::DIVIDE) {
    int type = token.type();
    token = lexer.nextToken();
    factorNode = factor();
    switch (type) {
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
    int type = token.type();
    token = lexer.nextToken();
    termNode = term();
    switch (type) {
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
  if(token.type() ==  Token::EQ){
    token = lexer.nextToken();
    reNode = new TreeNode(ISEQ, reNode, expression());
  }
  else if(token.type() == Token::LT){
    token = lexer.nextToken();
    reNode = new TreeNode(ISLT, reNode, expression());
  }
  else if(token.type() == Token::LE){
    token = lexer.nextToken();
    reNode = new TreeNode(ISLE, reNode, expression());
  }
  else if(token.type() == Token::GT){
    token = lexer.nextToken();
    reNode = new TreeNode(ISGT, reNode, expression());
  }
  else if(token.type() == Token::GE){
    token = lexer.nextToken();
    reNode = new TreeNode(ISGE, reNode, expression());
  }
  else if(token.type() == Token::NE){
    token = lexer.nextToken();
    reNode = new TreeNode(ISNE, reNode, expression());
  }
  else{
    error("Error in relation expression");
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
  //cout << "Program Accepted" << endl;
  return programNode;
}

void Parser::geninst(TreeNode *node)
{
  if (node != NULL) {
  geninst(node->leftChild);
  geninst(node->rightChild);
  
  switch (node->op) {
  case VAR:
    cout << "  push qword[" << node->val << "]" << endl;
    break;
    
  case LIT:
    cout << "  mov rax," << node->val << endl;
    cout << "  push rax" << endl;
    break;
    
  case ADD:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  add rax,rbx" << endl;
    cout << "  push rax" << endl;
    break;
    
  case SUB:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  sub rax,rbx" << endl;
    cout << "  push rax" << endl;
    break;
    
  case AND:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  and rax,rbx" << endl;
    cout << "  push rax" << endl;
    break;
    
  case OR:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  or rax,rbx" << endl;
    cout << "  push rax" << endl;
    break;
    
  case MULT:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  imul rbx" << endl;
    cout << "  push rax" << endl;
    break;
    
  case DIV:
    cout << "  mov rdx,0" << endl;
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  idiv rbx" << endl;
    cout << "  push rax" << endl;
    break;

  case STORE:
    cout << "  pop qword[" << node->val << "]" << endl;
    break;
    
  case LABEL:
    emit(node->val);
    break;
    
  case ISEQ:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  cmp rax,rbx" << endl;
    cout << "  je J" <<jmp << endl;
    cout << "  mov rax,0" << endl;
    cout << "  jmp J"<<jmp+1 << endl;
    cout << "J"<<jmp<<":" << endl;
    cout << "  mov rax,1" << endl;
    cout << "J"<<jmp+1<<":" << endl;
    cout << "  push rax" << endl;
    jmp+=2;
    break;
  
  case ISNE:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  cmp rax,rbx" << endl;
    cout << "  jne J" <<jmp << endl;
    cout << "  mov rax,0" << endl;
    cout << "  jmp J" << jmp+1 << endl;
    cout << "J"<<jmp<<":" << endl;
    cout << "  mov rax,1" << endl;
    cout << "J"<<jmp+1<<":" << endl;
    cout << "  push rax" << endl;
    jmp+=2;
    break;
    
    
  case ISLT:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  cmp rax,rbx" << endl;
    cout << "  jl J" <<jmp << endl;
    cout << "  mov rax,0" << endl;
    cout << "  jmp J" << jmp+1 << endl;
    cout << "J"<<jmp<<":" << endl;
    cout << "  mov rax,1" << endl;
    cout << "J"<<jmp+1<<":" << endl;
    cout << "  push rax" << endl;
    jmp+=2;
    break;

    
  case ISLE:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  cmp rax,rbx" << endl;
    cout << "  jle J" <<jmp << endl;
    cout << "  mov rax,0" << endl;
    cout << "  jmp J" << jmp+1 << endl;
    cout << "J"<<jmp<<":" << endl;
    cout << "  mov rax,1" << endl;
    cout << "J"<<jmp+1<<":" << endl;
    cout << "  push rax" << endl;
    jmp+=2;
    break;

    
  case ISGT:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  cmp rax,rbx" << endl;
    cout << "  jg J" << jmp << endl;
    cout << "  mov rax,0" << endl;
    cout << "  jmp J" << jmp+1 << endl;
    cout << "J" << jmp<<":" << endl;
    cout << "  mov rax,1" << endl;
    cout << "J"<<jmp+1<<":" << endl;
    cout << "  push rax" << endl;
    jmp+=2;
    break;

    
  case ISGE:
    cout << "  pop rbx" << endl;
    cout << "  pop rax" << endl;
    cout << "  cmp rax,rbx" << endl;
    cout << "  jge J" <<jmp << endl;
    cout << "  mov rax,0" << endl;
    cout << "  jmp J" << jmp+1 << endl;
    cout << "J"<<jmp<<":" << endl;
    cout << "  mov rax,1" << endl;
    cout << "J"<<jmp+1<<":" << endl;
    cout << "  push rax" << endl;
    jmp+=2;
    break;
    
  case JUMP:
    //emit(node->op, node->val);
    cout << "  jmp " << node->val <<endl;
    break;
    
  case JUMPF:
    //emit(node->val + ":");
    cout << "  pop rax" << endl;
    cout << "  cmp rax,0" << endl;
    cout << "  je " << node->val << endl;
    break;
    
  case JUMPT:
    //emit(node->op, node->val);
    cout << "  pop rax" << endl;
    cout << "  cmp rax,0" << endl;
    cout << "  jne" << node->op << endl;
    break;
    
  case PRINT:
    cout << "  pop rax" << endl;
    cout << "  push rbp" << endl;
    cout << "  mov rdi,fmt" << endl;
    cout << "  mov rsi,rax" << endl;
    cout << "  mov rax,0" << endl;
    cout << "  call printf" << endl;
    cout << "  pop rbp" << endl;
    cout << "  mov rax,0" << endl;
    break;
    
  case PRINTLN:
    cout << "  push rbp" << endl;
    cout << "  mov rdi,endl" << endl;
    cout << "  mov rax,0" << endl;
    cout << "  call printf" << endl;
    cout << "  pop rbp" << endl;
    cout << "  mov rax,0" << endl;
    break;
    
    
  default:
    //emit(node->op);
    break;
  }
  }
}
void Parser::vardefs(TreeNode *node)
{
  
  if (node != NULL) {
    if (node->op == VAR || node->op == STORE) {
      
      bool check = true;
    for (int j = 0; j < i; j++) {
      if (var[j] == node->val) {
	check = false;
      }
    }
    if (check) {
      var[i] = node->val;
      i++;
      
    }
  }
   vardefs(node->leftChild);
    vardefs(node->rightChild); 

  }
  
}

void Parser::genasm(TreeNode *node)
{
  cout << "  global main" << endl;
  cout << "  extern printf" << endl;
  cout << "  segment .bss" << endl;
  vardefs(node);
  for (int j = 0; j < i; j++) {
    cout << "  " << var[j] << " resq 1" << endl;
  }
  cout << "  section .data" << endl;
  cout << "fmt: db '%ld ', 0" << endl;
  cout << "endl: db 10, 0" << endl;
  cout << "  section .text" << endl;
  cout << "main:" << endl;
  geninst(node);
  cout << "  ret" << endl;
}

