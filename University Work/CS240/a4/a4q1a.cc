#include <iostream>
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
		index+=(unsigned int)(s[a]);
	}
	return index % 10000;

}

int myhash(string s) {
	unsigned long index=0;
	bool flag = true;
	for(int a = 0 ; a < s.length() ; a++) {
		if(flag)
		index+=(unsigned int)(s[a]);
		else 
		index *=(unsigned int)(s[a]);
		
		flag = !flag;
	}
	return index % 10000;

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

	int size = 10000;
	List table [size]; 
	string line;

	
	if(strcmp(argv[1], "-n") ==0) {
		ifstream s(argv[2]);
		//cout <<"-n option selected"<<endl;
		while(getline(s,line)) {
			if(!line.empty())
				table[myhash(line)].add(line);
		}
		s.close();
	
	}
	else {
		ifstream s(argv[1]);
		while(getline(s,line)) {
			if(!line.empty())
				table[hash(line)].add(line);
		}
		s.close();
	
	}
	
	

	printLongestChain(table, size);
	
}