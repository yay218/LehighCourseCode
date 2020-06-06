/* CSE 109
   Yang Yi
   yay218
   Program Description: This program creates a data structure called
   an Array Map which is more efficient than Hash Table when the
   number of objects is less than 1000
   Program #3
*/
   
//This is the header file arraymap.h. This is the interface
//for the class ArrayMap, which is a class for a array map
//of strings.

#ifndef ARRAYMAP_H
#define ARRAYMAP_H

#include <string>
#include "item.h"

using std::string;


class ArrayMap
{
public:
  ArrayMap(); //A default constructor that takes no arguments

  ~ArrayMap(); //Destructor
  
  void put(string key, int value);
  //Hash the key and put the hashed value into the proper place
  //in the hashedValues array
  //Then it insert the proper item into the arrayMap.

  int get(string key);
  //returns the value for the given key or -1 if it’s not found

  int remove(string key);
  //removes a value and returns it or -1 if it’s not found

private:
  int *hashedValues; //keys hashed values in ascending order
  Item **arrayMap; //pointers contain values and corresponding keys
  int arraySize; //The size of the two arrays

  int hash(string key); //hashes a key to unique integer value
  void shiftCompaction(int index); //Compact two arrays at given index
  void shiftInsert(int index); //Shifts to make room for a new value
  void reallocate();
  void deallocate();

  int binarySearchInsert(int hashedValue);
  //binary search to find the index of item should be inserted

  int binarySearchRetrieve(int hashedValue);
  //binary search to find the index of the given hashedValue

}; //ArrayMap
#endif //ARRAYMAP_H

