/**
	Simple driver for range2s
	Compile: g++ -D__TESTER__ -o range2 range2Tester.cc range2.cc 
*/
#include "range2.h"
#include <stdio.h>

void tellEdward(const Point& x)
{
	printf("(%d %d)\n", x.y, x.z);
}

int main()
{
	RangeTree2D t;
	Point x;
	int lowy;
	int highy;
	int lowz;
	int highz;

	while(scanf("(%d %d) ", &x.y, &x.z) == 2)
		t.add(x);
	scanf("[%d %d] [%d %d]", &lowy, &highy, &lowz, &highz);
	t.rangeSearch(lowy, highy, lowz, highz);
}
