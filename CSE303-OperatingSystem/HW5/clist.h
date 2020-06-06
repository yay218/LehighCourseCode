#pragma once
#include <thread>
#include <mutex>

std::mutex mtx;

/// TODO: complete this implementation of a thread-safe (concurrent) sorted
/// linked list of integers
class clist
{
	/// a Node consists of a value and a pointer to another node
	struct Node
	{
		int value;
		Node* next;
	};

	/// The head of the list is referenced by this pointer
	Node* head;
	int size = 0;

public:
	clist(int)
	: head(NULL)
	{}

	/// insert *key* into the linked list if it doesn't already exist; return
	/// true if the key was added successfully.
	bool insert(int key)
	{
		mtx.lock();
		//print();
		//printf("Inserting %d \n", key);
		Node *current = new Node;
	  	current->value = key;

	  	if(head == NULL) {
	  		head = current; 
	  		size++;
	  	}
	  	else if(current->value < head->value) {
	  		current->next = head;
	  		head = current;
	  	}
	  	else if(current->value == head->value) {
	  		mtx.unlock();
	  		return false;
	  	}
	  	else {
	  		Node *pointer = head;
	  		while((pointer->next) && (pointer->next->value < current->value))
			{
			  	pointer = pointer->next;
			}
			if(pointer->next)
			{
				if(pointer->next->value == current->value)
				{
					mtx.unlock();
					return false;
				}
			}
			current->next=pointer->next;
			pointer->next = current;
			size++;
		}

	    //print();
	    //printf("\n", getSize());
	    mtx.unlock();
		return true;
	}
	/// remove *key* from the list if it was present; return true if the key
	/// was removed successfully.
	bool remove(int key)
	{
		mtx.lock();
		//print();
		//printf("Removing %d \n", key);
	  	bool removed = false;
	  	Node* pointer = head;
	  	Node* last = NULL;
	  	while(pointer != NULL) {
	      	if(pointer->value == key) {
	          	if(pointer == head) {
	              	head = pointer->next;
	              	
	          	}
	          	else if(pointer->next == NULL) {
	              	last->next = NULL;
	          	}
	          	else {
	              	last->next = pointer->next;
	          	}
	          	delete pointer;
	          	//free(pointer);
		  		removed = true;
	          	break;
	      	}
	      	last = pointer;
	      	pointer = pointer->next;
	  	}
	  	//print();
	  	//printf("\n", getSize());
	    mtx.unlock();
	  	return removed;
	}
	/// return true if *key* is present in the list, false otherwise
	bool lookup(int key) const
	{
		mtx.lock();
		//printf("Finding %d \n", key);
	  	Node* current = head;
		Node* last = NULL;
	  	while (current != NULL) {
			if(current->value == key)
			{
				//printf("true\n");
				mtx.unlock();
				return true;
			}
			last = current;
			current = current->next;
	  	}
	    mtx.unlock();
	  	return false;
	}

	void print() const
	{
		if (head != NULL)
		{
			printf("%d", head->value);
			Node* current = head->next;
			while(current != NULL)
			{
				printf(" %d", current->value);
				current = current->next;
			}
		}
		printf("\n");
	}


	//The following are not tested by the given tester but are required for grading
	//No locks are required for these.
	size_t getSize() const
	{
		return size;
	}
	int getElement(size_t idx) const
	{
		return 0;
	}


	//These functions just need to exist, they do not need to do anything
	size_t getBucketSize(size_t bucket) const
	{
		return 0;
	}
	int getElement(size_t bucket, size_t idx) const
	{
		return 0;
	}
};
