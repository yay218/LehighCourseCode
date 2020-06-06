#include <iostream>
#include <fstream>
#include <cstring>
#include "parser.h"

using namespace std;

int main(int argc, char **argv)
{
  ifstream input;
  input.open(argv[1]);
  
  Lexer *lexeme = new Lexer(input);
  Parser *parser = new Parser(*lexeme, cout);
  Parser::TreeNode *tree = parser -> program();
  string print = Parser::TreeNode::toString(tree);
  cout << print << endl;
  
}
