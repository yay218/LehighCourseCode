/* CSE 109
   Yang Yi
   yay218
   Program Description: This program creates a data structure called
   an Array Map which is more efficient than Hash Table when the
   number of objects is less than 1000
   Program #3
*/

//This is the implementation file arraymap.cc.
//This is the implementation of the class ArrayMap.
#include <iostream>
#include <string>
#include <cstdlib>
#include "arraymap.h"


using std::string;
using std::cout;
using std::endl;

ArrayMap::ArrayMap():arraySize(0)
{
  /*Body intentionally empty*/
}

ArrayMap::~ArrayMap()
{
  if (arrayMap!=NULL)
    delete [] arrayMap;
  if(hashedValues!=NULL)
    delete [] hashedValues;
}

void ArrayMap::put(string key, int value)
{
  Item *newItem = new Item(key, value);
  int insertIndex = binarySearchInsert(hash(key));
  shiftInsert(insertIndex);
  hashedValues[insertIndex] = hash(key);
  arrayMap[insertIndex] = newItem;
  arraySize++;
}

int ArrayMap::get(string key)
{
  int index = binarySearchRetrieve(hash(key));
  bool find = false;
  if (index == -1) return -1;
  if (index == 0) return arrayMap[index]->getValue();
  if (index == arraySize-1) return arrayMap[index]->getValue();

  for (int i=index;i<arraySize;i++) {
    if ((arrayMap[i]->getKey()).compare(key)==0){
      index = i;
      find = true;
    }
  }
  if (!find){
    for (int i = index;i>=0;i--) {
      if ((arrayMap[i]->getKey()).compare(key)==0){
	index =i;
	find = true;
      }
    }
  }

  if(!find) return -1;
  return arrayMap[index]->getValue();
}


int ArrayMap::remove(string key)
{
  int removeIndex = binarySearchRetrieve(hash(key));
  int value = arrayMap[removeIndex]->getValue();
  if (removeIndex == -1) return -1;
  shiftCompaction(removeIndex);
  arraySize--;
  return value;
}


int ArrayMap::hash(string key)
{
  int hash = 0;
  for (unsigned int i = 0; i < key.length(); i++) {
    hash = hash + key[i];
  }
  return hash;

}

void ArrayMap::shiftCompaction(int index)
{
  for (int i = index; i < arraySize; i++) {
    hashedValues[i] = hashedValues[i+1];
    arrayMap[i] = arrayMap[i+1];
  }
  deallocate();
}

void ArrayMap::shiftInsert(int index)
{
  reallocate();
  for (int i = arraySize; i > index; i--) {
    hashedValues[i] = hashedValues[i-1];
    arrayMap[i] = arrayMap[i-1];
  }
}

void ArrayMap::reallocate()
{
  int *newHashedValues = (int*)malloc((arraySize+1)*sizeof(int));
  Item **newArrayMap = (Item**)malloc((arraySize+1)*sizeof(Item));
  for (int i = 0; i < arraySize; i++) {
    newHashedValues[i] = hashedValues[i];
    newArrayMap[i] = arrayMap[i];
  }

  hashedValues = (int*)malloc((arraySize+1) * sizeof(int));
  arrayMap = (Item**)malloc((arraySize+1) * sizeof(Item*));
  for (int i = 0; i < arraySize+1; i++) {
    hashedValues[i] = newHashedValues[i];
    arrayMap[i] = newArrayMap[i];
  }
}

void ArrayMap::deallocate()
{
  int *newHashedValues = (int*)malloc((arraySize-1)*sizeof(int));
  Item **newArrayMap = (Item**)malloc((arraySize-1)*sizeof(Item));
  for (int i = 0; i < arraySize; i++) {
    newHashedValues[i] = hashedValues[i];
    newArrayMap[i] = arrayMap[i];
  }

  hashedValues = (int*)malloc((arraySize-1) * sizeof(int));
  arrayMap = (Item**)malloc((arraySize-1) * sizeof(Item*));
  for (int i = 0; i < arraySize-1; i++) {
    hashedValues[i] = newHashedValues[i];
    arrayMap[i] = newArrayMap[i];
  }
}

int ArrayMap::binarySearchInsert(int hashedValue)
{
  int left = 0;
  int right = arraySize-1;
  int middle;
  while (left <= right) {
    middle = (left + right) / 2;
    if (hash(arrayMap[middle]->getKey()) == hashedValue)
      return middle + 1;
    else if (hash(arrayMap[middle]->getKey()) > hashedValue)
      right = middle - 1;
    else
      left = middle + 1;
  }
  return left;
}

int ArrayMap::binarySearchRetrieve(int hashedValue)
{
  int left = 0;
  int right = arraySize - 1;
  while (left <= right) {
    int middle = (left + right) / 2;
    if (hash(arrayMap[middle]->getKey()) == hashedValue)
      return middle;
    else if (hash(arrayMap[middle]->getKey()) > hashedValue)
      right = middle - 1;
    else
      left = middle + 1;
  }
  return -1;

}
