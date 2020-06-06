#pragma once
#include <thread>
#include <mutex>

/// TODO: complete this implementation of a thread-safe (concurrent) hash
///       table of integers, implemented as an array of linked lists.  In
///       this implementation, each list should have a "sentinel" node that
///       contains the lock, so we can't just reuse the clist implementation
class shash
{
	struct Node
	{
		int value;
		Node* next;
	};

	struct Sentinel_Node
	{
		Node* sentinel_value;
		std::mutex mtx;
	};

	Node* head;
	Sentinel_Node* sentinel_node;
	
public:
	int length;
	Sentinel_Node** buckets;
	shash(unsigned _buckets)
	{
		buckets = new Sentinel_Node*[length];
		length = _buckets;
		for(int i = 0; i < length; i++) {
			head = new Node;
			sentinel_node = new Sentinel_Node;
			sentinel_node->sentinel_value = head;
			buckets[i] = sentinel_node;
		}
	}


	/// insert *key* into the appropriate linked list if it doesn't already
	/// exist; return true if the key was added successfully.
	bool insert(int key)
	{
		sentinel_node->mtx.lock();
		//print();
		//printf("Inserting %d \n", key);
		Node *current = current = (struct Node *) malloc(sizeof(struct Node));
	  	current->value = key;

	  	if(head == NULL) {
	  		head = current; 
	  	}
	  	else if(current->value < head->value) {
	  		current->next = head;
	  		head = current;
	  	}
	  	else if(current->value == head->value) {
	  		sentinel_node->mtx.unlock();
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
					sentinel_node->mtx.unlock();
					return false;
				}
			}
			current->next=pointer->next;
			pointer->next = current;
		}
		//print();
	    sentinel_node->mtx.unlock();
		return true;	
	}
	/// remove *key* from the appropriate list if it was present; return true
	/// if the key was removed successfully.
	bool remove(int key)
	{
	  	sentinel_node->mtx.lock();
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
	          	delete pointer;
		  		removed = true;
	          	break;
	      	}
	      	last = pointer;
	      	pointer = pointer->next;
	  	}
	  	//print();
	    sentinel_node->mtx.unlock();
	  	return removed;
	}
	/// return true if *key* is present in the appropriate list, false
	/// otherwise
	bool lookup(int key) const
	{
	  	sentinel_node->mtx.lock();
	  	//print();
	  	//printf("Finding %d \n", key);
	  	Node* current = head;
		Node* last = NULL;
	  	while (current != NULL) {
			if(current->value == key)
			{
				//printf("true\n");
				sentinel_node->mtx.unlock();
				return true;
			}
			last = current;
			current = current->next;
	  	}
	    sentinel_node->mtx.unlock();
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

	//This refers to the number of buckets not the total number of elements.
	size_t getSize() const
	{
		return 0;
	}

	//This refers to the number of elements in a bucket, not the sentinel node.
	size_t getBucketSize(size_t bucket) const
	{
		return 0;
	}
	int getElement(size_t bucket, size_t idx) const
	{
		return 0;
	}


	//These functions just need to exist, they do not need to do anything
	int getElement(size_t idx) const
	{
		return 0;
	}
};
