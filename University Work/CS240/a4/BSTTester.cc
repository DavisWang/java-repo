/**
	Simple driver for BSTs
	Compile: g++ -D__TESTER__ -o BST BSTTester.cc BST.cc 
*/
#include "BST.h"
#include <stdio.h>

void tellEdward(int x)
{
	printf("%d\n", x);
}

int main()
{
	BinarySearchTree t;	
	int x;
	int low;
	int high;

	while(scanf("%d", &x) == 1)
		t.add(x);
	scanf("[%d %d]", &low, &high);
	t.rangeSearch(low, high);
}
