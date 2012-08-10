#include <iostream>
#include <fstream>
#include <cmath>
#include <string>
#include <cstring>
using namespace std;

int len = 0; //stores the number of of words in the chain
string chain; //stores the deepest insertion probe string encountered

//the first hash function
int hashb (string s) {
	int index=0;
	for(int a = 0 ; a < s.length() ; a++) {
		index+=(unsigned int)(s[a]);
	}
	//cout <<s <<"    "  <<index << endl;
	return index % 50077;

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
	return index % 50077;

}

//the second hash function.
int hashc (string s) {
	long index = 0;
	int len = s.length();
	for(int a = 0 ; a < len ; a++) {
		index+= ((unsigned int)(s[a]) * pow(3, len-a));
	}
	return 1+(index % (50077 - 1));
}

//gets the next available slot according to double hashing, keeps stepping
//the second hash value forward until an empty slot is found.
//will encounter infinite loop if trying to insert more than the hash table size
int nextAvailSlot(string table [], string s, int start, int size) {
	int hash = hashc(s);
	int a = start+1;
	int probelen = 0;
	string probechain;
	while(true) {
		probelen++;
		probechain.append(table[a]+" ");
		
		a= (a+hash)%50077;
	    //cout << a << "      " << hash  << "word is:" << s << "    "  <<"word at this loc is :"<<table[a]<< endl;
		if(table[a].empty()) {
			if(probelen>len) {
			len = probelen;
			chain = probechain;
			}
			return a;
		}
	}
}

int main (int argc, char * argv[]) {
	
	int size = 50077;
	string table [size]; 
	string line;
	
	if(strcmp(argv[1], "-n") ==0) {
	
	ifstream s(argv[2]);
		while(getline(s,line)) {
		if(!line.empty()) {
			int index = myhash(line);
			if(table[index].empty())
				table[index] = line; //add if no collision
			else
				{
				index = nextAvailSlot(table,line, index, size); //find next avail slot
				table[index] = line;
				}
		}
	}
	s.close();
	}
	else {
	ifstream s(argv[1]);
		while(getline(s,line)) {
		if(!line.empty()) {
			int index = hashb(line);
			if(table[index].empty())
				table[index] = line; //add if no collision
			else
				{
				index = nextAvailSlot(table,line, index, size); //find next avail slot
				table[index] = line;
				}
		}
	}
	s.close();
	}
	
	
	//prints out the length and the longest probe operation
	cout << len << endl;
	cout << chain << endl;
	
	
}