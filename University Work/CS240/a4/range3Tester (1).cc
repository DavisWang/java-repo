/**
	Simple driver for range3s
	Compile: g++ -D__TESTER__ -o range3 range3Tester.cc range3.cc 
*/
#include "range3.h"
#include <stdio.h>

void tellEdward(const Point& x)
{
	printf("(%d %d %d)\n", x.x, x.y, x.z);
}

int main()
{
	RangeTree3D t;
	Point x;
	int lowx;	
	int highx;
	int lowy;
	int highy;
	int lowz;
	int highz;

	while(scanf("(%d %d %d) ", &x.x, &x.y, &x.z) == 3)
		t.add(x);
	scanf("[%d %d] [%d %d] [%d %d]", &lowx, &highx, &lowy, &highy, &lowz, &highz);
	t.rangeSearch(lowx, highx, lowy, highy, lowz, highz);
}
