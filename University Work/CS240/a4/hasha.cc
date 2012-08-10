#include <iostream>
#include <fstream>
#include <cmath>
#include <string>
#include <cstring>
using namespace std;

//Node class
class Node {
  public:
    Node() {};
	string value;
    Node* next;
};

// List class
class List {
    
  public:
    Node *head;
    List() { head = NULL; };
	~List() {
		
	delete head;
	};
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
	
	//outputs the length of the linked list
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

//original hash function
int hash(string s) {
	int index=0;
	for(int a = 0 ; a < s.length() ; a++) {
		index+=(unsigned int)(s[a]);
	}
	return index % 10000;

}

//improved hash function, I alternate between addition and
//multiplication to make order matter more, and use an unsigned long
//to hold that large number
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

//this method prints the longest sized linked list as well as counting
//this number of linked list with size 0
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
	List * table [size]; 
	string line;

	//option -n
	if(strcmp(argv[1], "-n") ==0) {
		ifstream s(argv[2]);
		//cout <<"-n option selected"<<endl;
		while(getline(s,line)) {
			if(!line.empty())//add the line to the hash table if the line is not empty
				table[myhash(line)].add(line);
		}
		s.close();
	
	}
	else {
		ifstream s(argv[1]);
		while(getline(s,line)) {
			if(!line.empty()) //add the line to the hash table if the line is not empty
				table[hash(line)].add(line);
		}
		s.close();
	
	}

	printLongestChain(&table, size); //print out the longest chain
	
	for(int a =0; a< size ;a++) {
		Node * temphead = table[a]->head;
		while(temphead!=NULL) {
			temphead= temphead->next;
			delete temphead;
		}
		delete table[a];
	}
	delete[] table;
	
}