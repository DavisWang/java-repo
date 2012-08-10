#include <iostream>
#include <string>

using namespace std;

int kmp (string p, string t) {
	int f [p.length()]; //declare array of p length

	//init kmp fail array
	f[0] = 0;
	int i = 1; 
	int j = 0;
	while (i < p.length()) {
		if (p[i] == p[j] || p[j] == '?') { //added wildcard condition
			f[i] = j+1;
			i++;
			j++;
		}
		else if (j > 0) {
			j = f[j-1];
		}
		else {
			f[i] = 0;
			i++;
		}
	}
	//kmp fail array initialization ends here

	/*
	for(int a=0 ;a<p.length(); a++) {
		cout << f[a] << " ";
	}
	cout <<endl;
	*/

	i = 0; //reset variables
	j = 0;

	//kmp algorithm starts here
	while(i< t.length()) {
		if(t[i] == p[j] || p[j] == '?') //added condition to take wildcard into account
			if(j == p.length()-1)
				return i-j; //found
			else {
				i++;
				j++;
			}
		else {
			if (j > 0)
				j = f[j-1];
			else 
				i++;
		}
	}

	return -1; //no match
}


int main (int argc, char * argv[]) {

	string p = argv[1];
	string t = argv[2];
	//cout << argv[1] << " " << argv[2] << endl;

	int result = kmp(p,t); //calls kmp with arguments

	if(result == -1)
		cout << "No" << endl;
	else 
		cout << result << endl;
}