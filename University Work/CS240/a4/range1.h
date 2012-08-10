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
  point, left, and right child - self-explanatory */
  //changed value to point
  Point point;  
  BSTNode* left;
  BSTNode* right;
public:
/** 
  BSTNode v l r -> (BSTNode)
  Constructs a BST node */
  BSTNode(const Point& p);
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
  bool add(const Point& p);
/**
  search v -> exists?
  Checks to see if v is in the tree */
  bool search(const Point& p);
  
  void rangeSearch(int low, int high);
  //a bunch of getters
  BSTNode * getLeft();
  BSTNode * getRight();
  int getX();
  int getY();
  int getZ();
  Point getPoint();
};

/** RangeTree1D - Implement */
class RangeTree1D { 
public:
  BSTNode * root;
  RangeTree1D();
  ~RangeTree1D();
  bool add(const Point &p); 
  bool search(const Point &p); 
  // Prints out points with zlo <= z <= zhi 
  void rangeSearch(const int zlo, const int zhi);
};


#endif
