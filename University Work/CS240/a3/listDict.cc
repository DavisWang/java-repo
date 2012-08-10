#include <iostream>
#include <stdlib.h>
#include <cmath>
#include <string>
#include <cstring>
using namespace std;

// Node class
class Node {
  public:
    Node() {};
	int value;
    Node* next;
};

// List class
class List {
    Node *head;
  public:
    List() { head = NULL; };
	//used in initializing the list, this adds a new node to the end of the list
	void add(int value) {
		Node * newNode = new Node();
		newNode->value = value;
		newNode->next = NULL;
		
		Node *temphead = head;
		
		if ( temphead != NULL ) {
			while ( temphead->next != NULL ) {
				temphead = temphead->next;
			}
			temphead->next = newNode;
		}
		else {
			head = newNode;
		}
	
	};
	
	//swaps two values based on value
    void swap(int value, int value2) {

		Node * temphead = head;
	
		while(temphead != NULL) {
			
			if(temphead->value == value) {
			temphead->value = value2;
			}
			else if (temphead->value == value2) {
			temphead->value = value;
			}
			
			temphead = temphead->next;
		}

	};
	// prints the list, for debugging only
    void print() {
		Node * temphead = head;
		
		if (temphead == NULL) {
			cout << "EMPTY" << endl;
		}
		else {
			do {
				cout << temphead->value << " <-- ";
				temphead = temphead->next;
			} while(temphead != NULL);
			cout << "NULL" << endl;
		}
	};
	
	//query is just this method with the returned value +1
	 int getIndexForValue(int value) {
 
		Node * temphead = head;
		
		int counter = 0;
		
		while(temphead != NULL) {
		
			if(temphead->value == value) return counter;
		
			counter++;
			temphead = temphead->next;
		}
	 };
 
	//get the value given an index for the list
	 int getValueAtIndex(int index) {
		Node * temphead = head;
		int counter = 0;
		while(temphead != NULL) {
		
			if(counter == index) return temphead->value;
			counter++;
			temphead = temphead->next;
		}
	 };
	 
	 //move a given value to the front of the list by swapping it with
	 //the value just before it until it's at the front
	 void moveUp(int value) {
		if(getIndexForValue(value) != 0)
			while(getIndexForValue(value) != 0) {
				swap(getValueAtIndex(getIndexForValue(value)-1), value);
			}
	 };
    
};

//a rng for uniform distribution between 1-100
int uniform() {
	return rand() % 100 + 1;
}

//a rng for exponential distribution between 1-100
int exponential() {
	return 101-log2(1/((double) rand()/RAND_MAX));
}

//a rng for linear distribution between 1-100
int linear () {
	double x = (double) rand()/RAND_MAX;
	return sqrt(10100*x);
}


int main(int argc, char * argv[])
{
    // New list
    List list;
	
	//char * argv[1] = argv[1],argv[2]=argv[2];
	int training = atoi(argv[3]);
	int trials = atoi(argv[4]);
	srand (time(NULL)); //inits the random seed
	/*
	cout << argv[1] << endl;
	cout << argv[2] << endl;
	cout << training << endl;
	cout << trials << endl;
	*/
	
	//init list
	for(int a = 1 ; a<=100;a++) {
		list.add(a);
	}
	
	int value;
	//training
	
	for(int a = 0 ; a < training ; a++) {
		if(strcmp(argv[1], "h1") == 0) {
			if(strcmp(argv[2], "uniform") == 0) {
				value = uniform();
				list.moveUp(value);
			}
			else if(strcmp(argv[2], "linear") == 0) {
				value = linear();
				list.moveUp(value);
			}
			else if(strcmp(argv[2], "exponential") == 0) {
				 value = exponential();
				list.moveUp(value);
			}
		}
		
		else if(strcmp(argv[1], "h2") == 0) {
			if(strcmp(argv[2], "uniform") == 0) {
				 value = uniform();
				if(list.getIndexForValue(value) != 0)
					list.swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
			else if(strcmp(argv[2], "linear") == 0) {
				 value = linear();
				if(list.getIndexForValue(value) != 0)
					list.swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
			else if(strcmp(argv[2], "exponential") == 0) {
				 value = exponential();
				if(list.getIndexForValue(value) != 0)
					list.swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
		}
	}
	
	//trials
	int totalNodeVisited = 0;

	for(int a = 0 ; a < trials ; a++) {
		//heuristics = h1
		if(strcmp(argv[1], "h1") == 0) {
			if(strcmp(argv[2], "uniform") == 0) {
				 value = uniform();				
				totalNodeVisited+=list.getIndexForValue(value)+1;
				list.moveUp(value);
			}
			else if(strcmp(argv[2], "linear") == 0) {
				value = linear();				
				totalNodeVisited+=list.getIndexForValue(value)+1;
				list.moveUp(value);

			}
			else if(strcmp(argv[2], "exponential") == 0) {
				 value = exponential();
				totalNodeVisited+=list.getIndexForValue(value)+1;
				list.moveUp(value);
			}
		}
		
		//heuristics = h2
		else if(strcmp(argv[1], "h2") == 0) {
			if(strcmp(argv[2], "uniform") == 0) {
				 value = uniform();
				totalNodeVisited+=list.getIndexForValue(value)+1;
				if(list.getIndexForValue(value) != 0)
					list.swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
			else if(strcmp(argv[2], "linear") == 0) {
				 value = linear();
				totalNodeVisited+=list.getIndexForValue(value)+1;
				if(list.getIndexForValue(value) != 0)
					list.swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
			else if(strcmp(argv[2], "exponential") == 0) {
				 value = exponential();
				totalNodeVisited+=list.getIndexForValue(value)+1;
				if(list.getIndexForValue(value) != 0)
					list.swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
		}
		
		// no heuristics
		else {
			if(strcmp(argv[2], "uniform") == 0) {
				 value = uniform();
				totalNodeVisited+=list.getIndexForValue(value)+1;
			}
			else if(strcmp(argv[2], "linear") == 0) {
				 value = linear();
				totalNodeVisited+=list.getIndexForValue(value)+1;
			}
			else if(strcmp(argv[2], "exponential") == 0) {
				 value = exponential();
				totalNodeVisited+=list.getIndexForValue(value)+1;
			}
		}
	}
	
	//prints out the final avg number of nodes visited per query
	cout << (double)totalNodeVisited/trials << endl;

}