/* CSE 109
   Yang Yi
   yay218
   Program Description: This program creates a data structure called
   an Array Map which is more efficient than Hash Table when the
   number of objects is less than 1000
   Program #3
*/


#ifndef ITEM_H
#define ITEM_H

#include <string>

using std::string;

class Item
{
public:
  Item(string key, int value); //The constructor
  string getKey(); //returns the key for the given item
  int getValue(); //returns the value for the given item

private:
  string key; //private member variable
  int value; //private member variable   

}; //Item
#endif
