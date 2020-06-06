#pragma once

/// TODO: complete this implementation of a thread-safe (concurrent) AVL
///       tree of integers, implemented as a set of Node objects.
///       In addition, the API now allows for multiple insertions on each
///       operation.
///       Less than goes to the left. Greater than goes to the right.
class tree
{
//This is for testing purposes. Do not access your Node directly.
public:
	struct Node
	{
	public:
		int value;
		Node* left;
		Node* right;
	};
	Node* head;

public:
	tree(int)
	: head(NULL)
	{}

	/// insert /num/ values from /data/ array into the tree, and return the
	/// success/failure of each insert in /results/ array.
	void insert(int* data, bool* results, int num)
	{}
	/// remove *data* from the list if it was present; return true if the data
	/// was removed successfully.
	void remove(int* data, bool* results, int num)
	{}
	/// return true if *data* is present in the list, false otherwise
	void lookup(int* data, bool* results, int num) const
	{}

	//The following are not tested by the given tester but are required for grading
	//No locks are required for these.

	//Total number of elements in the tree
	size_t getSize() const
	{
		return 0;
	}

	int getElement(size_t idx) const
	{
		return getElement(0, head);
	}


	//These functions need to exist, they do not need to do anyting
	size_t getBucketSize() const
	{
		return 0;
	}
	int getElement(size_t bucket, size_t idx) const
	{
		return 0;
	}



private:
	//Returns the idx'th element from the tree assuming an inorder traversal.
	int getElement(size_t idx, Node* at) const
	{
		return 0;
	}
};
