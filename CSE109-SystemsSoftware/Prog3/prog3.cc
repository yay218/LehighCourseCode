/* CSE 109
   Yang Yi
   yay218
   Program Description: This program creates a data structure called
   an Array Map which is more efficient than Hash Table when the
   number of objects is less than 1000
   Program #3
*/


#include <string>
#include <iostream>
#include "arraymap.h"
#include "item.h"

using std::string;
using std::cout;
using std::endl;

int main(int argc, char **argv) {
  ArrayMap *arrayMap = new ArrayMap();
  arrayMap->put("hello", 5);
  arrayMap->put("helol", 10); //To produce a collision
  arrayMap->put("lemon", 20);
  arrayMap->put("dog", 25);
  arrayMap->put("track", 35);
  arrayMap->put("computer", 45);
  arrayMap->put("ball", 50);
  arrayMap->put("lehigh", 60);
  cout << "Result hello: " << arrayMap->get("hello") << endl;
  cout << "Result helol: " << arrayMap->get("helol") << endl;
  cout << "Result lemon: " << arrayMap->get("lemon") << endl;
  cout << "Result dog: " << arrayMap->get("dog") << endl;
  cout << "Result track: " << arrayMap->get("track") << endl;
  cout << "Result computer: " << arrayMap->get("computer") << endl;
  cout << "Result ball: " << arrayMap->get("ball") << endl;
  cout << "Result lehigh: " << arrayMap->get("lehigh") << endl;
  cout << "Result keyboard: " << arrayMap->get("keyboard") << endl;
  cout << "Get: " << arrayMap->get("helol") << endl; //To remove a collision
  cout << "Remove: " << arrayMap->remove("helol") << endl;
  cout << "After remove: " << arrayMap->get("helol") << endl;
  return 0;
}
