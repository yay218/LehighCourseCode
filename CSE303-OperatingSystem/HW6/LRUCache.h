#include <mutex>
#include <strings.h>
using namespace std;


class LRUCache {
  	struct Node
  	{
    	char* filename;
      int size;
      char* content;
    	Node* prev = NULL;
    	Node* next = NULL;
  	};
    Node* head = NULL;
    Node* tail = NULL;
  	int capacity;
    mutable mutex mtx;

public:
    LRUCache (int size) {
      capacity = size;
    }

    Node* get(char* filename) {
      Node* x = head;
      while(x) {
        if (strcmp(filename, x->filename)==0) {
          //mtx.unlock();
          recentUse(x);
          //printf("%d\n", x->size);
          return x;
        }
        x = x->next;
      }
      return NULL;
    }

    int getFileSize(char* filename) {
      mtx.lock();
      Node* x = get(filename);
      if (x) {
        mtx.unlock();
        return x->size;
      }
      mtx.unlock();
      return -1;
    }

    char* getFileContent(char* filename) {
      mtx.lock();
      Node* x = get(filename);
      if (x) {
        mtx.unlock();
        return x->content;
      }
      mtx.unlock();
      return NULL;
    }

    
    void put(char* filename, int size, char* content) {
      mtx.lock();
      //cur_put ++;
      //printf("%d\n", cur_put);
      // if (strcmp(get(filename), "error")!=0) {
      if (get(filename)!=NULL) {
        //printf("Already exist in cache\n");
        bzero(tail->content, size);
        strncpy(tail->content, content, size);
        strncat(tail->content, "\0", 1);
        //printf("CACHE SIZE: %d\n", getSize());
        mtx.unlock();
        return;
      }
      if (getSize() >= capacity) {
        removeLRU();
      }
      Node* target = new Node;
      // store information
      target->filename = new char[strlen(filename)];
      strcpy(target->filename, filename);
      target->size = size;
      target->content = new char[size];
      strcpy(target->content, content);
      // memmove(target->content, content, strlen(content));
      // head and tail
      // link
      if (tail != NULL) {
        tail->next = target;
        target->prev = tail;
        tail = target;
      }
      else {
        tail = target;
      }
      if (!head) { 
        head = target;
      }
      //printf("put in cache\n");
      //printf("CACHE SIZE: %d\n", getSize());
      //puts(target->content);
      mtx.unlock();
      return;
    }

    void update(char* filename, int size, char* content) {
      Node* x = get(filename);
      if (!x) {
        printf("Cannot find the file in cache.\n");
      }
      else {
        strcat(x->content, content);
      }
      return;
    }


    void recentUse(Node* x) {
      // if x is tail
      if (x->next==NULL) {
        return;
      }
      // if x is head and x is not tail
      else if (x->prev==NULL) {
        head = x->next;
        head->prev = NULL;
        tail->next = x;
        x->prev = tail;
        x->next = NULL;
        tail = x;
        return;
      }
      // if x in the middle
      else {
        x->prev->next = x->next;
        x->next->prev = x->prev;
        x->prev = tail;
        x->next = NULL;
        tail->next = x;
        tail = x;
        return;
      }
    }

    void removeLRU() {
      if (head) {
        Node* target = head;
        head = head->next;
        head->prev = NULL;
        delete target;
      }
      return;
    }

    int getSize() {
      if (!head) {
        return 0;
      }
      Node* x = head;
      int cnt = 0;
      while(x) {
        cnt += 1;
        x = x->next;
      }
      return cnt;
    }
};