#include <iostream>
#include <fstream>
#include <cstring>
#include "parser.h"

using namespace std;
bool genCode = true;

void processFile(istream& in) {
  Lexer lexer(in);
  Token token;
  Parser parser(lexer, cout);
  Parser::TreeNode* program = parser.program();
  if (genCode) {
    parser.genasm(program);
  } else {
    cout << Parser::TreeNode::toString(program) << endl;
  }
}


int main(int argc, char **argv) {
  ifstream in;

  if (argc > 1) {
    string a1 = argv[1];
    in.open(argv[1]);
    processFile(in);
    in.close();
    return 0;
  } else {
    processFile(cin);
  }



/*
bool genCode = true;

int main(int argc, char **argv)
{
  ifstream input;
  input.open(argv[1]);
  
  Lexer *lexeme = new Lexer(input);
  Parser *parser = new Parser(*lexeme, cout);
  Parser::TreeNode *tree = parser -> program();
  if (genCode) {
    parser->genasm(tree);
  } else {
    cout << Parser::TreeNode::toString(tree) << endl;
  }
*/

  /*
  Parser::TreeNode *asem = parser -> genasm(tree);
  string print = Parser::TreeNode::toString(tree);
  cout << print << endl;
  */
  
}
