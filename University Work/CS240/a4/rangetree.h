/**
  range?.h
  Starter file for CS 240 Data Structures and Data Management,
  Assignment 4.

  Brad Lushman, Alex Lopez-Oritz, Edward Lee, Spring 2012.
*/

#ifndef __RANGE_H__
#define __RANGE_H__

#include <ostream>


/** BEGIN POINT DT */
struct Point {
  /** Co-ordinates in R^3 */
  int x, y, z;
};
/**
  tellEdward p -> (state)
  Gives to the autotesting framework [i.e tells Edward] a point or an integer */
void tellEdward(const Point& p);
void tellEdward(int v);

/** BEGIN BINARY SEARCH TREE */
class BSTNode {
private:
/**
  Value, left, and right child - self-explanatory */
  int value;  
  BSTNode* left;
  BSTNode* right;
public:
/** 
  BSTNode v l r -> (BSTNode)
  Constructs a BST node */
  BSTNode(int v, BSTNode *l = NULL, BSTNode *r = NULL);
/** ~BSTNode
    Cleanup destructor
  Implementor's note:
    Make sure you don't leak memory */
  ~BSTNode();
public:
/**
  add v -> !exists?
  Adds v to the binary tree rooted at this.
    returns true if it does not exist, false otherwise */
  bool add(int v);
/**
  search v -> exists?
  Checks to see if v is in the tree */
  bool search(int v);
};

class BinarySearchTree {
private:
/**
  Root node */
  BSTNode* root;
public:
/** 
  BinarySearchTree -> (BST)
  Default constructor */
  BinarySearchTree();
/**
  ~BinarySearchTree
  Make sure you free everything */
  ~BinarySearchTree();
public:
/**
  add, search
  Wrappers around BSTNode */
  bool add(int v);
  bool search(int v);
  void rangeSearch(int lo, int hi);
};
/** END BINARY SEARCH TREE */

#endif
