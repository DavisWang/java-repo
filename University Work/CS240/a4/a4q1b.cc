#include <iostream>
#include <fstream>
#include <cmath>
#include <string>
#include <cstring>
using namespace std;

int hashb (string s) {
	int index=0;
	for(int a = 0 ; a < s.length() ; a++) {
		index+=(unsigned int)(s[a]);
	}
	//cout <<s <<"    "  <<index << endl;
	return index % 50077;

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
	return index % 50077;

}

int nextAvailSlot(string table [], int start, int size) {
	for(int a = start+1 ; a < size ; a++) {
		if(table[a].empty()) {
			//cout << "next avail slot" << a % size << endl;
			return a;
		}
	}
	for(int a = 0 ; a < start ; a++) {
		if(table[a].empty()) {
			//cout << "next avail slot" << a % size << endl;
			return a;
		}
	}
}

void printLongestCluster(string table[], int size) {
		int counter = 0;
		int longest=0;
		int lbegin=0;
		int lend=0;
		int begin=-1;
		int end=0;
		
		int a =0;
		bool looped=false;
		while(a < size) {
			if(!table[a].empty() && begin ==-1) {
				begin = a;
				counter++;
			}
			else if(!table[a].empty()) {
				counter++;
			}
			else {
				end = a;
				if(counter > longest) {
					lbegin = begin;
					lend = end;
					longest = counter;
					//cout << lbegin << " " << lend << " " << longest<<endl;
					
				}
				begin = -1;
				counter = 0;
			}
		a++;
		if(a == size && !table[size-1].empty())
			if(!looped) {
				a=0;
				looped=true;
			}
		}
		
		//cout << lbegin << " " << lend << endl;
		
		if (counter == size) {
			cout << counter << endl;
		for(int a =0; a< size ;a++) {
			cout << table[a] << " ";
		}
		cout << endl;
		}
		else {
			cout << longest<<endl;
			if(lbegin > lend) {
				for(int a = lbegin; a< size;a++)
					cout << table[a] << " ";
				for(int a = 0 ; a< lend; a++) {
					cout << table[a] << " ";
				}
				cout << endl;
			}
			else {

				for (int a = lbegin; a< lend;a++) {
					cout << table[a] << " ";
				}			
				cout << endl;
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
					table[index] = line;
				else
					table[nextAvailSlot(table, index, size)] = line;
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
					table[index] = line;
				else
					table[nextAvailSlot(table, index, size)] = line;
			}
		}
		s.close();
	}
	
	
	
	
	
	printLongestCluster(table, size);
	/*
	for(int a=0;a<size;a++) {
		cout << table[a] << endl;
	}
	*/
	
	
}