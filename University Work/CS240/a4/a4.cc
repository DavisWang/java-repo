#include <iostream>
#include <stdlib.h>
#include <fstream>
#include <cmath>
#include <string>
#include <cstring>
using namespace std;

class Node {
  public:
    Node() {};
	string value;
    Node* next;
};

// List class
class List {
    Node *head;
  public:
    List() { head = NULL; };
	//used in initializing the list, this adds a new node to the end of the list
	void add(string value) {
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
	
	int length() {
		Node * temphead = head;
		int len=0;
		if(temphead == NULL)
			return len;
		else {
			do {
				len+=1;
				temphead= temphead->next;
			} while(temphead!=NULL);
			return len;
		}
	};

	// prints the list
    void print() {
		Node * temphead = head;
		
		if (temphead == NULL) {
			cout << endl;
		}
		else {
			do {
				cout << temphead->value << " ";
				temphead = temphead->next;
			} while(temphead != NULL);
			cout << endl;
		}
	};
    
};

int hash(string s) {
	int index=0;
	for(int a = 0 ; a < s.length() ; a++) {
		index+=(int)(s[a]);
	}
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

void printLongestChain(List table [], int size) { 
	int counter=0;
	int max=0;
	int maxindex=0;
	for(int a = 0 ; a < size; a++) {
		if(table[a].length() == 0)
			counter++;
		if(table[a].length() > max) {
			max = table[a].length();
			maxindex = a;
		}
	}
	cout << max << endl;
	table[maxindex].print();
	cout << counter << endl;
}



int main (int argc, char * argv[]) {

	string file = argv[1];
	int size = 50077;
	List table [size]; 
	string line;
	ifstream s(argv[1]);
	
	while(getline(s,line)) {
		//cout << "word: " << line << endl;
		table[hashb(line)].add(line);
	}
	s.close();
	/*
	cout << "TEST" << endl;
	for(int b = 0; b < 10 ; b++) {
		for(int a = b ; a<=b+10;a++) {
			table[b].add("a");
		}
	}
	for(int b = 0; b < 10 ; b++) {
		cout << table[b].length() << endl;
		table[b].print();
		cout << endl;
	}
	*/

	printLongestChain(table, size);
	
	
	
	
	
}