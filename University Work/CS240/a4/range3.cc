/**
  range?.cc
  Starter file for CS 240 Data Structures and Data Management,
  Assignment 4.

  Brad Lushman, Alex Lopez-Oritz, Edward Lee, Spring 2012
*/

#include <cassert>
#include <iostream>
#include "range3.h"
using namespace std;

// a constructor to initialize a point in a BSTNode
BSTNode::BSTNode(const Point& p) {
	point.z = p.z;
	point.y = p.y;
	point.x = p.x;
	left = NULL;
	right = NULL;
	tree = NULL;
}

/** ~BSTNode
    Cleanup destructor
  Implementor's note:
    Make sure you don't leak memory */
BSTNode::~BSTNode()
{
  delete left;
  delete right;
  delete tree;
}

RangeTree2D::RangeTree2D() {
	root = NULL;
}
RangeTree3D::RangeTree3D() {
	root = NULL;
}

RangeTree3D::~RangeTree3D(){
	delete root;
}

bool BSTNode::add3D(const Point & p) {
  if(point.x == p.x) {
	if(tree == NULL) { //create a new tree if tree is null
		tree = new BSTNode(p);
		tree->add2D(p);
		return true;
	}
	else //otherwise just add to tree
		return tree->add2D(p);
	}
  else if(p.y > point.y)
    if(right)
      return right->add3D(p); //add to right subtree if right is not null
    else {
      right = new BSTNode(p); //create a new node with that point
	  right->tree = new BSTNode(p);
	  right->tree->add2D(p);
	  }
  else //same logic here, for left side
    if(left)
      return left->add3D(p);
    else {
      left = new BSTNode(p);
	  left->tree = new BSTNode(p);
	  left->tree->add2D(p);
	  }
  return true;
}

//add2D calls add to add to subtree once it has added in the d-dimension
bool BSTNode::add2D(const Point & p) {
  if(point.y == p.y) {
	if(tree == NULL) { //create a new tree if tree is null
		tree = new BSTNode(p);
		return true;
	}
	else //otherwise just add to tree
		return tree->add(p);
	}
  else if(p.y > point.y)
    if(right)
      return right->add2D(p); //add to right subtree if right is not null
    else {
      right = new BSTNode(p); //create a new node with that point
	  right->tree = new BSTNode(p); //and a subtree
	  }
  else //same logic here, for left side
    if(left)
      return left->add2D(p);
    else {
      left = new BSTNode(p);
	  left->tree = new BSTNode(p);
	  }
  return true;
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

bool BSTNode::search3D(const Point & p) {
  if(point.x == p.x)
    return getTree()->search2D(p); //search the subtree if x coord matches
  else if(p.x > point.x && right)
   return right->search3D(p); //recursive call
  else if(p.x < point.x && left)
    return left->search3D(p); //same here
  else
    return false;
}

bool BSTNode::search2D(const Point & p) {
  if(point.y == p.y)
    return getTree()->search(p); //search the subtree if y coord matches
  else if(p.y > point.y && right)
   return right->search2D(p); //recursive call
  else if(p.y < point.y && left)
    return left->search2D(p); //same here
  else
    return false;
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

BSTNode * BSTNode::getTree() {
	return tree;
}

BSTNode * BSTNode::getLeft() {
	return left;
}

BSTNode * BSTNode::getRight() {
	return right;
}


void BSTNode::rangeSearch3D(int xlo, int xhi, int ylo,int yhi, int zlo, int zhi) {
	if(getX() < xlo) { //search the right tree if it's not null
		if(getRight()!=NULL)
			getRight()->rangeSearch3D(xlo, xhi, ylo,yhi,zlo,zhi);
	}
	
	if(getX() > xhi) { //serach the left tree if it's not null
		if(getLeft()!=NULL)
			getLeft()->rangeSearch3D(xlo,xhi,ylo,yhi,zlo,zhi);
	}
	
	//otherwise if it's in the range, print it and 
	//search both child if they're not null
	if(getX() <= xhi && getX() >= xlo) { 
		//cout << "printing point:" <<endl;
		//tellEdward(getPoint());
		getTree()->rangeSearch2D(ylo,yhi,zlo,zhi);
		// cout << "end print point" <<endl;
		if(getRight()!=NULL)
			getRight()->rangeSearch3D(xlo,xhi,ylo,yhi,zlo,zhi);
		if(getLeft()!=NULL)
			getLeft()->rangeSearch3D(xlo,xhi,ylo,yhi,zlo,zhi);
	}
}

//this method calls rangeSearch to search in 1 dimentions, this
//method merely finds the nodes for which the y coord lies in the range
//then we run rangesearch on the subtrees of those nodes
void BSTNode::rangeSearch2D(int ylo,int yhi, int zlo, int zhi) {
	if(getY() < ylo) { //search the right tree if it's not null
		if(getRight()!=NULL)
			getRight()->rangeSearch2D(ylo,yhi,zlo,zhi);
	}
	
	if(getY() > yhi) { //serach the left tree if it's not null
		if(getLeft()!=NULL)
			getLeft()->rangeSearch2D(ylo,yhi,zlo,zhi);
	}
	
	//otherwise if it's in the range, print it and 
	//search both child if they're not null
	if(getY() <= yhi && getY() >= ylo) { 
		//cout << "printing point:" <<endl;
		//tellEdward(getPoint());
		getTree()->rangeSearch(zlo,zhi);
		// cout << "end print point" <<endl;
		if(getRight()!=NULL)
			getRight()->rangeSearch2D(ylo,yhi,zlo,zhi);
		if(getLeft()!=NULL)
			getLeft()->rangeSearch2D(ylo,yhi,zlo,zhi);
	}
}

//the rangeSearch function that prints stuff out
//this is the version that rangeSearches in one dimension
//the two others rangesearch functions that take 6 and 4 params
//searches in 3d and 2d respectively
//the call hierarchy is rangesearch3d calls rangesearch2d, which calls this rangesearch,
//which prints stuff out.
void BSTNode::rangeSearch(int lo, int hi) {
	if(getZ() < lo) { //search the right tree if it's not null
		if(getRight()!=NULL) {
			getRight()->rangeSearch(lo,hi);}
	}

	if(getZ() > hi) { //serach the left tree if it's not null
		if(getLeft()!=NULL){
			getLeft()->rangeSearch(lo,hi);}
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
  if(root)
	return root->add(p);
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

void RangeTree2D::rangeSearch(const int ylo, const int yhi, const int zlo, const int zhi) {
	if(root == NULL)
		return;
	root->rangeSearch2D(ylo,yhi,zlo,zhi);
}

bool RangeTree2D::add(const Point &p) {
	if(root)
		return root->add2D(p);
	//a wrapper method around a BSTNode::add
	else {
		root = new BSTNode(p);
		root->add2D(p);
		return true;
	}
}

bool RangeTree2D::search(const Point &p) {
	if(root)
		return root->search2D(p);
	else return false;
}

void RangeTree3D::rangeSearch(const int xlo, const int xhi, const int ylo, const int yhi, const int zlo, const int zhi) {
	if(root == NULL)
		return;
	root->rangeSearch3D(xlo,xhi,ylo,yhi,zlo,zhi);
}

bool RangeTree3D::add(const Point &p) {
	if(root!=NULL) {
		return root->add3D(p); }
	//a wrapper method around a BSTNode::add
	else {
		root = new BSTNode(p);
		root->add3D(p);
		return true;
	}
}

bool RangeTree3D::search(const Point &p) {
	if(root)
		return root->search3D(p);
	else return false;
}

/*
int main( )
{ 
  RangeTree1D x;
  Point p1;
  Point p2;
  Point p3;
  Point p4;
  Point p5;

  p1.x = 1;
  p2.x = 1;
  p3.x = 2;
  p4.x = 3;
  p5.x = 4;
  
  p1.y = 1;
  p2.y = 2;
  p3.y = 2;
  p4.y = 3;
  p5.y = 4;
  
  p1.z = 1;
  p2.z = 2;
  p3.z = 3;
  p4.z = 3;
  p5.z = 4;

  x.add(p1);
  x.add(p2);
  x.add(p3);
  x.add(p4);
  x.add(p5);
  
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


