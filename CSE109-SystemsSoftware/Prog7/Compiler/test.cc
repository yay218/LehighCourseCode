/* CSE 109
   Yang Yi
   yay218
   Program Description: This program develops a lexical analyzer,
   which will act as a tokenizer for the compiler we are developing in
   class.
   Program #4
*/

#include <iostream>
#include <string>
#include <fstream>
#include "lexer.h"
#include "token.h"

using namespace std;

int main(int argc, char **argv)
{
  ifstream input;
  input.open(argv[1]);
  Lexer *lexeme = new Lexer(input);
  Token token = lexeme->nextToken();
  cout << "Type\tLexeme\tLine #\tPos\n";
  while(token.type() != 30) {
    cout << token.type() << "\t" << token.lexeme() << "\t" << token.line() << "\t" << token.pos() << endl;
    token = lexeme->nextToken();
  }
}
