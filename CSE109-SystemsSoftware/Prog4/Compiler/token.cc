/* CSE 109
   Yang Yi
   yay218
   Program Description: This program develops a lexical analyzer,
   which will act as a tokenizer for the compiler we are developing in
   class.
   Program #4
*/

#include <string>
#include "token.h"

using std::string;

Token::Token(int tokenType, string lex, int lineNumber, int position):
  tokenType(tokenType),lex(lex),lineNumber(lineNumber),position(position)
{
  /*Body intentionally empty*/
}

Token::~Token()
{
  /*Nothing to destroy yet*/
}

int Token::type()
{
  return tokenType;
}

string Token::lexeme()
{
  return lex;
}

int Token::line()
{
  return lineNumber;
}

int Token::pos()
{
  return position;
}
