#include <iostream>
#include <stdlib.h>
#include <cmath>
#include <cstring>
using namespace std;

// Node class
class Node {
  public:
    Node() {};
	int value;
    Node* next;
    void setValue(int v) { 
		value = v; 
	};
    void setNext(Node* n) { 
		next = n; 
	};

};

// List class
class List {
    Node *head;
  public:
    List() { head = NULL; };
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
	//swaps based on value
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
 
	 int getValueAtIndex(int index) {
		Node * temphead = head;
		int counter = 0;
		while(temphead != NULL) {
		
			if(counter == index) return temphead->value;
			counter++;
			temphead = temphead->next;
		}
	 }; 
    
};

int uniform() {
	return rand() % 100 + 1;
}

int exponential() {
	return 101-log2(1/((double) rand()/RAND_MAX));
}

int linear () {
	double x = (double) rand()/RAND_MAX;
	return sqrt(10100*x);
}


int main(int argc, char * argv[])
{
    // New list
    List list;
	
	string heuristic = argv[1],dist=argv[2];
	int training = atoi(argv[3]);
	int trials = atoi(argv[4]);
	srand (time(NULL));
	/*
	cout << heuristic << endl;
	cout << dist << endl;
	cout << training << endl;
	cout << trials << endl;
	*/
	
	//init list
	for(int a = 1 ; a<=100;a++) {
		list.add(a);
	}
	
	//training
	for(int a = 0 a < training ; a++) {
		if(strcmp(heuristic, "h1") == 0) {
			if(strcmp(dist, "uniform") == 0) {
				int value = uniform();
				swap(list.getValueAtIndex(0), value);
			}
			else if(strcmp(dist, "linear") == 0) {
				int value = linear();
				swap(list.getValueAtIndex(0), value);
			}
			else if(strcmp(dist, "exponential") == 0) {
				int value = exponential();
				swap(list.getValueAtIndex(0), value);
			}
		}
		
		else if(strcmp(heuristic, "h2") == 0) {
			if(strcmp(dist, "uniform") == 0) {
				int value = uniform();
				if(list.getIndexForValue(value) != 0)
					swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
			else if(strcmp(dist, "linear") == 0) {
				int value = linear();
				if(list.getIndexForValue(value) != 0)
					swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
			else if(strcmp(dist, "exponential") == 0) {
				int value = exponential();
				if(list.getIndexForValue(value) != 0)
					swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
		}
	}
	
	//trials
	int totalNodeVisited = 0;

	for(int a = 0 ; a < trials ; a++) {
		if(strcmp(heuristic, "h1") == 0) {
			if(strcmp(dist, "uniform") == 0) {
				int value = uniform();				
				totalNodeVisited+=list.getIndexForValue(value)+1;
				swap(list.getValueAtIndex(0), value);
			}
			else if(strcmp(dist, "linear") == 0) {
				int value = linear();				
				totalNodeVisited+=list.getIndexForValue(value)+1;
				swap(list.getValueAtIndex(0), value);

			}
			else if(strcmp(dist, "exponential") == 0) {
				int value = exponential();
				totalNodeVisited+=list.getIndexForValue(value)+1;
				swap(list.getValueAtIndex(0), value);
			}
		}
		
		else if(strcmp(heuristic, "h2") == 0) {
			if(strcmp(dist, "uniform") == 0) {
				int value = uniform();
				totalNodeVisited+=list.getIndexForValue(value)+1;
				if(list.getIndexForValue(value) != 0)
					swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
			else if(strcmp(dist, "linear") == 0) {
				int value = linear();
				totalNodeVisited+=list.getIndexForValue(value)+1;
				if(list.getIndexForValue(value) != 0)
					swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
			else if(strcmp(dist, "exponential") == 0) {
				int value = exponential();
				totalNodeVisited+=list.getIndexForValue(value)+1;
				if(list.getIndexForValue(value) != 0)
					swap(list.getValueAtIndex(list.getIndexForValue(value)-1), value);
			}
		}
		
		else {
			if(strcmp(dist, "uniform") == 0) {
				int value = uniform();
				totalNodeVisited+=list.getIndexForValue(value)+1;
			}
			else if(strcmp(dist, "linear") == 0) {
				int value = linear();
				totalNodeVisited+=list.getIndexForValue(value)+1;
			}
			else if(strcmp(dist, "exponential") == 0) {
				int value = exponential();
				totalNodeVisited+=list.getIndexForValue(value)+1;
			}
		}
	}
	
	
	cout << (double)totalNodeVisited/trials << endl;
	
	/*
	for(int a = 1 ; a<=100;a++) {
		list.add(a);
		cout << uniform() << " ";
	}
	cout << endl;
	cout << endl;
	
	for(int a = 1 ; a<=100;a++) {
		cout << linear() << " ";
	}
	cout << endl;
	cout << endl;
	
	for(int a = 1 ; a<=100;a++) {
		cout << exponential() << " ";
	}
	cout << endl;
	cout << endl;
	*/
	//list.print();

	
	/*
    // Append nodes to the list
    list.add(1);
	
    list.print();
	
    list.add(2);
    list.print();
    list.add(3);
    list.print();
    list.add(4);
    list.print();
    list.add(5);
    list.print();
	
	list.swap(list.getValueAtIndex(1),list.getValueAtIndex(2));
	list.print();
	list.swap(list.getValueAtIndex(2),list.getValueAtIndex(3));
	list.print();
	
	list.swap(list.getValueAtIndex(list.getIndexForValue(1)),list.getValueAtIndex(list.getIndexForValue(2)));
	list.print();
	list.swap(list.getValueAtIndex(list.getIndexForValue(2)),list.getValueAtIndex(list.getIndexForValue(3)));
	list.print();
	list.swap(list.getValueAtIndex(list.getIndexForValue(2)),list.getValueAtIndex(list.getIndexForValue(3)));
	list.print();
	list.swap(list.getValueAtIndex(list.getIndexForValue(1)),list.getValueAtIndex(list.getIndexForValue(2)));
	list.print();
	*/


}