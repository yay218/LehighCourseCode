/* CSE 109
   Yang Yi
   yay218
   Program Description: This program creates a data structure called
   an Array Map which is more efficient than Hash Table when the
   number of objects is less than 1000
   Program #3
*/


#include <string>
#include "item.h"

using std::string;


Item::Item(string key, int value):key(key),value(value)
{
  /*Body intentionally empty*/
}

string Item::getKey()
{
  return key;

}

int Item::getValue()
{
  return value;

}
