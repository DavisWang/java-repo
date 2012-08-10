/**
  range?.cc
  Starter file for CS 240 Data Structures and Data Management,
  Assignment 4.

  Brad Lushman, Alex Lopez-Oritz, Edward Lee, Spring 2012
*/

#include <cassert>
#include <iostream>
#include "BST.h"
using namespace std;


/** 
  BSTNode v l r -> (BSTNode)
  Constructs a BST node */
BSTNode::BSTNode(int v, BSTNode* l, BSTNode* r) :
  value(v), left(l), right(r) {}
/** ~BSTNode
    Cleanup destructor
  Implementor's note:
    Make sure you don't leak memory */
BSTNode::~BSTNode()
{
  delete left;
  delete right;
}

/**
  add v -> !exists?
  Adds v to the binary tree rooted at this.
    returns true if it does not exist, false otherwise */
bool BSTNode::add(int v)
{
  if(value == v)
    return false;
  else if(v > value)
    if(right)
      return right->add(v);
    else
      right = new BSTNode(v);
  else
    if(left)
      return left->add(v);
    else
      left = new BSTNode(v);
  return true;
}

/**
  search v -> exists?
  Checks to see if v is in the tree */
bool BSTNode::search(int v)
{
  if(value == v)
    return true;
  else if(v > value && right)
   return right->search(v);
  else if(v < value && left)
    return left->search(v);
  else
    return false;
}

int BSTNode::getValue() {
	return value;
}

BSTNode * BSTNode::getLeft() {
	return left;
}

BSTNode * BSTNode::getRight() {
	return right;
}

/** 
  BinarySearchTree -> (BST)
  Default constructor */
BinarySearchTree::BinarySearchTree() : 
  root(NULL) {}

/**
  ~BinarySearchTree
  Make sure you free everything */
BinarySearchTree::~BinarySearchTree()
{
  delete root;
}

/**
  add, search
  Wrappers around BSTNode */
bool BinarySearchTree::add(int v)
{
  if(!root)
    root = new BSTNode(v, NULL, NULL);
  else
    return root->add(v);
  return true;
}

bool BinarySearchTree::search(int v)
{
  if(!root)
    return false;
  else
    return root->search(v);
}


void BinarySearchTree::rangeSearch(int lo, int hi) {
	if(root == NULL) { //search is done if there is no tree to search
		return;
	}
	root->rangeSearch(lo,hi); //execute BSTNode::rangeSearch
}

void BSTNode::rangeSearch(int lo, int hi) {
	
	
	if(getValue() < lo) { //search the right tree if it's not null
		if(getRight()!=NULL)
			getRight()->rangeSearch(lo,hi);
	}
	
	if(getValue() > hi) { //serach the left tree if it's not null
		if(getLeft()!=NULL)
			getLeft()->rangeSearch(lo,hi);
	}
	
	//otherwise if it's in the range, print it and 
	//search both child if they're not null
	if(getValue() <= hi && getValue() >= lo) { 
		tellEdward(getValue());
		if(getRight()!=NULL)
			getRight()->rangeSearch(lo,hi);
		if(getLeft()!=NULL)
			getLeft()->rangeSearch(lo,hi);
	}
}


/*
int main( )
{ 
  BinarySearchTree x;
  x.add(52);
  x.add(35);
  x.add(74);
  x.add(15);
  x.add(42);
  x.add(65);
  x.add(97);
  x.add(9);
  x.add(27);
  x.add(39);
  x.add(46);
  x.add(60);
  x.add(69);
  x.add(86);
  x.add(99);

  x.rangeSearch(30,65);
  
  return 0;
}

*/

/** Sample Driver 
    Notes on tellEdward output specification:
      a list of points (x y z) or integers
      ex: (1 2 3) <newline> (4 5 6) ....
  The code enclosed by __TESTER__ will NOT be compiled
  when we are doing release and public tests.
  
  We will be #include'ing range?.h and we will expect
  that the functions and methods expected are defined.

  We will be linking against your code in the following fashion:
  g++ -D__TESTER__ -Wall -g range?.cc ourTester.cc -o range? 
  
  We will provide our own main and our own tellEdward for Point's and integers.

  DO NOT MOVE THE CODE BELOW OUTSIDE OF THE PREPROCESSOR BLOCK*/
#ifndef __TESTER__
/**
  tellEdward p -> (state)
  Outputs a point or an integer */
void tellEdward(const Point& p) {
  cout << "(" << p.x << " " << p.y << " " << p.z << ")" << endl;
}
void tellEdward(int v) {
  cout << v << endl;
}

/** Testing search + add in a binary search tree */
void testsimpleBST()
{
  BinarySearchTree x;
  assert(x.add(1));
  assert(!x.add(1));
  assert(x.add(2));
  assert(x.add(3));
  assert(x.add(4)); 
  assert(!x.add(2));
  assert(x.add(-1));

  assert(x.search(1));
  assert(x.search(2));
  assert(x.search(3));
  assert(x.search(4));
  assert(x.search(-1));
}

/** Testing Simple RangeSearch
  (this code intentionally left guarded out) */
#if DONE_RANGESEARCH
void testsimpleRS()
{
  BinarySearchTree x;
  assert(x.add(1));
  assert(!x.add(1));
  assert(x.add(2));
  assert(x.add(3));
  assert(x.add(4)); 
  assert(!x.add(2));
  assert(x.add(-1));

  /** Should display 2 <newline> 3 <newline> 4 */
  x.rangeSearch(2,4);
}
#endif

#endif


