#pragma once
#include <pthread.h>

pthread_rwlock_t lock;

/// TODO: complete this implementation of a thread-safe (concurrent) sorted
/// linked list of integers, which should use readers/writer locking.
class rwlist
{
	/// a node consists of a value and a pointer to another node
	struct Node
	{
		int value;
		Node* next;
	};

	/// The head of the list is referenced by this pointer
	Node* head;


public:
	rwlist(int)
	: head(NULL)
	{}


	/// insert *key* into the linked list if it doesn't already exist; return
	/// true if the key was added successfully.
	bool insert(int key)
	{
		pthread_rwlock_wrlock(&lock);
		//print();
		//printf("Inserting %d \n", key);
		bool inserted = false;
		Node *current = new Node;
	  	current->value = key;

	  	if(head == NULL) {
	  		head = current; 
	  	}
	  	else if(current->value < head->value) {
	  		current->next = head;
	  		head = current;
	  	}
	  	else if(current->value == head->value) {
	  		pthread_rwlock_unlock(&lock);
	  		return false;
	  	}
	  	else {
	  		struct Node *pointer = head;
	  		while((pointer->next) && (pointer->next->value < current->value))
			{
			  	pointer = pointer->next;
			}
			if(pointer->next)
			{
				if(pointer->next->value == current->value)
				{
					pthread_rwlock_unlock(&lock);
					return false;
				}
			}
			current->next=pointer->next;
			pointer->next = current;
		}

	    //print();
	    pthread_rwlock_unlock(&lock);
		return true;
	}
	/// remove *key* from the list if it was present; return true if the key
	/// was removed successfully.
	bool remove(int key)
	{
		pthread_rwlock_rdlock(&lock);
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
	          	//free(pointer);
		  		removed = true;
	          	break;
	      	}
	      	last = pointer;
	      	pointer = pointer->next;
	  	}
	  	//print();
	    pthread_rwlock_unlock(&lock);
	  	return removed;
	}
	/// return true if *key* is present in the list, false otherwise
	bool lookup(int key) const
	{
		pthread_rwlock_rdlock(&lock);
		//print();
	  	//printf("Finding %d \n", key);
	  	Node* current = head;
		Node* last = NULL;
	  	while (current != NULL) {
			if(current->value == key)
			{
				//printf("true\n");
				pthread_rwlock_unlock(&lock);
				return true;
			}
			last = current;
			current = current->next;
	  	}
	    pthread_rwlock_unlock(&lock);
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
		return 0;
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
