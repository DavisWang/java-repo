#include <iostream>
#include <stdlib.h>
#include <cmath>
#include <string>
#include <cstring>
using namespace std;

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
    
};

int hash(string s) {
	int index=0;
	for(int a = 0 ; a < s.length() ; a++) {
		index+=(int)(s[a]);
	}
	cout << index;
	return index % 10000;

}

int hashc (string s) {
	int index = 1;
	int len = s.length();
	for(int a = 0 ; a < len ; a++) {
		index+= ((int)(s[a]) * pow(3, len-a));
	}
	
	return index % (50077 - 1);
}

int hashb (string s) {
	int index=0;
	for(int a = 0 ; a < s.length() ; a++) {
		index+=(int)(s[a]);
	}
	return index % 50077;

}



int main (int argc, char * argv[]) {

	string file = argv[1];
	
	string line;
	ifstream stream(file);
	
	while(stream.is_open()) {
		getline(stream, line);
		cout << "word: " << line << endl;
	}
	stream.close();
	
	
	
	
	

}