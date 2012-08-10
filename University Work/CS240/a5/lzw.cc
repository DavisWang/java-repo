#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
#include "bitbuffer.h"

using namespace std;

BitBuffer b;
string d [1024];
int nextAvailSlot = 257;

//a function to convert from an integer in decimal to a string in binary
string toBinary (int d) {

	int r;
	stringstream ss;
	if(d < 2) {
		ss << d;
		return ss.str();
	}
	else {
		r = d % 2;
		ss << toBinary(d >> 1);
		ss << r;
		return ss.str();
	}
}

//a function to add an eof byte, only called once at the end of reading the file
void addEOF(){
	b.addBit('0');
	b.addBit('1');
	b.addBit('0');
	b.addBit('0');
	b.addBit('0');
	b.addBit('0');
	b.addBit('0');
	b.addBit('0');
	b.addBit('0');
	b.addBit('0');
}

//adds a string in binary to the bit buffer
void addToBitBuffer(string s) {
	
	//pad with 0s first
	for(int a =0 ; a< 10-s.length(); a++) {
		b.addBit('0');
		//cout << "0";
	}
	//followed by the bits of the string
	for(int a=0 ; a< s.length();a++) {
		b.addBit(s[a]);
		//cout << s[a];
	}
	//cout << endl;
}

//gets the index of the dictionary entry containing s, -1 if not found
int getCode(string s) {

	for(int a = 0 ; a < 1024 ; a++) {
		if(s.compare(d[a]) == 0) {
			return a;
		}
	}
	return -1;
}

//using a global variable that is incremented every time this function is called
//returns the next slot to be written to in the dictionary
//loops back to 257 after hitting 1024, the size of the dictionary
int getNextAvailSlot() {

	int slot;

	slot = nextAvailSlot;
	nextAvailSlot++;
	if(nextAvailSlot>=1024)
		nextAvailSlot = 257;

	return slot;
}

//inits the dictionary with extended ASCII character set
void initDictionary() {
	for(int a = 0 ; a < 256 ; a++)
		d[a] = (unsigned char)a;
	//d[256] = "EOF";
}

int main (int argc, char * argv[]) {

	ifstream s(argv[1]); //read from arg 1
	
	initDictionary();

	char c; //the next char in the stream
	string code="";
	c = s.get();
	code+=c; //init the string to the first character in the 

	if((int)c == -1) {
		addEOF(); //just add the eof char if there is nothing
	}
	else {
		while(true) {
			
			c=s.get(); //get next character
			if((int)c == -1) { //eof char
				addToBitBuffer(toBinary(getCode(code))); //add the last bit to buffer
				break;
			}
			//cout << "Char:" << c << endl;
			
			if(getCode(code+c) != -1) { //if the code exists in the dictionary, add the next char and continue
				code+=c;
			}
			else {
				//cout << "Adding:" << code+c << "|" << endl;
				d[getNextAvailSlot()] = code+c; //otherwise, add that string to the dictionary
				addToBitBuffer(toBinary(getCode(code))); //add the bits of the code to the buffer
				//cout << endl;
				code = c; //reinitialize the code with the last character
			}
		}
		addEOF(); //add the eof character
	}
		/*
		cout << "printing array" << endl;
		for(int a =0 ; a< 1024 ;a++)
			cout << d[a] << endl;
		*/
		ofstream os(argv[2]); //output to the file specified

		while(!b.isEmpty()) //expunge the bit buffer
			os << b.getByte();
	
}