/**
  range?.cc
  Starter file for CS 240 Data Structures and Data Management,
  Assignment 4.

  Brad Lushman, Alex Lopez-Oritz, Edward Lee, Spring 2012
*/

#include <cassert>
#include <iostream>
#include "range1.h"
using namespace std;

// a constructor to initialize a point in a BSTNode
BSTNode::BSTNode(const Point& p) {
	point = p;
	left = NULL;
	right = NULL;
}

/** ~BSTNode
    Cleanup destructor
  Implementor's note:
    Make sure you don't leak memory */
BSTNode::~BSTNode()
{
  delete left;
  delete right;
}

RangeTree1D::RangeTree1D() {
	root = NULL;
}

RangeTree1D::~RangeTree1D() {
	delete root;
}

//edited slightly compared to the BST.cc version to read the z coord from point p
bool BSTNode::add(const Point& p)
{
  if(point.z == p.z)
    return false;
  else if(p.z > point.z)
    if(right)
      return right->add(p);
    else
      right = new BSTNode(p);
  else
    if(left)
      return left->add(p);
    else
      left = new BSTNode(p);
  return true;
}

//edited slightly compared to the BST.cc version to read the z coord in the point p
bool BSTNode::search(const Point& p)
{
  if(point.z == p.z)
    return true;
  else if(p.z > point.z && right)
   return right->search(p);
  else if(p.z < point.z && left)
    return left->search(p);
  else
    return false;
}

Point BSTNode::getPoint() {
	return point;
}

int BSTNode::getX() {
	return point.x;
}

int BSTNode::getY() {
	return point.y;
}

int BSTNode::getZ() {
	return point.z;
}

BSTNode * BSTNode::getLeft() {
	return left;
}

BSTNode * BSTNode::getRight() {
	return right;
}

//the rangeSearch function that prints stuff out
//this is the version that rangeSearches in one dimension
//the two others rangesearch functions that take 6 and 4 params
//searches in 3d and 2d respectively
//the call hierarchy is rangesearch3d calls rangesearch2d, which calls this rangesearch,
//which prints stuff out.
void BSTNode::rangeSearch(int lo, int hi) {
	
	
	if(getZ() < lo) { //search the right tree if it's not null
		if(getRight()!=NULL)
			getRight()->rangeSearch(lo,hi);
	}
	
	if(getZ() > hi) { //serach the left tree if it's not null
		if(getLeft()!=NULL)
			getLeft()->rangeSearch(lo,hi);
	}
	
	//otherwise if it's in the range, print it and 
	//search both child if they're not null
	if(getZ() <= hi && getZ() >= lo) { 
		//cout << "printing point:" <<endl;
		tellEdward(getPoint());
		// cout << "end print point" <<endl;
		if(getRight()!=NULL)
			getRight()->rangeSearch(lo,hi);
		if(getLeft()!=NULL)
			getLeft()->rangeSearch(lo,hi);
	}
}

//a wrapper method around a BSTNode::add
bool RangeTree1D::add(const Point &p)
{
  if(root) {
	return root->add(p);
	}
  else {
	root = new BSTNode(p);
	return true;
  }
}

//a wrapper method around a BSTNode::search
bool RangeTree1D::search(const Point &p)
{
  if(root)
	return root->search(p);
  else return false;
}

//a wrapper method around a BSTNode::rangeSearch
void RangeTree1D::rangeSearch(const int zlo, const int zhi) {
	if(root == NULL) { //search is done if there is no tree to search
		return;
	}
	root->rangeSearch(zlo,zhi); //execute BSTNode::rangeSearch
}

/*
int main()
{ 
  RangeTree1D x;
  Point p1;
  Point p2;
  Point p3;
  Point p4;
  Point p5;

  p1.z = 1;

  x.add(p1);

  
  //cout << "added points" << endl;
  
  x.rangeSearch(1,5);
  
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


/** Testing Simple RangeSearch
  (this code intentionally left guarded out) */
#if DONE_RANGESEARCH


#endif

#endif


