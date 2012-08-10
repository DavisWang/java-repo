/**
 * by Davis Wang 
 * CS240 assignment 2 question 4
**/

#include <iostream>
#include <string>
#include <sstream>
#include  <fstream>
#include <vector>
#include <cstring>
#include <math.h>

using namespace std;

int count=0;
int counta=0;
int quickselect(vector<int>& A, int start, int end, int m);

int partition(vector<int>& input, int start, int end)
{

    int pivot = input[end];
    
    while ( start < end )
    {
        while ( input[start] < pivot ) {count++;
            start++;}
        
        while ( input[end] > pivot ){count++;
            end--;}
        
        if ( input[start] == input[end] ) {count++;
            start++;}
        else if ( start < end ) {
            int tmp = input[start];
            input[start] = input[end];
            input[end] = tmp;
        }
    }
	/*
	cout << "ARRAY   ";
	for(int a = 0 ; a < input.size(); a ++) {
		cout << input[a] << " ";
	}
	cout << endl;
	*/
	return end;
}

int partitiona(vector<int>& input, int start, int end, int location)
{
 
  int s = sqrt(input.size());
  float r = (float)location/(float)input.size();
  float index = ceil(r*s);
  
  vector<int> i2;

  for(int a = 0 ; a< s ; a++) {
    i2.push_back(input [a]);
  
  }
  //cout << s << " " << r << " " << index << endl;
  int pivot = quickselect(i2, 0, s, index);
    
    
    while ( start < end )
    {
        while ( input[start] < pivot ) {counta++;
            start++;}
        
        while ( input[end] > pivot ){counta++;
            end--;}
        
        if ( input[start] == input[end] ) {counta++;
            start++;}
        else if ( start < end ) {
            int tmp = input[start];
            input[start] = input[end];
            input[end] = tmp;
        }
    }
	/*
	cout << "ARRAY   ";
	for(int a = 0 ; a < input.size(); a ++) {
		cout << input[a] << " ";
	}
	cout << endl;
	*/
	return end;
}


void swap (vector<int>& input, int i, int j) {
	int temp = input[i];
	input[i] = input[j];
	input[j] = temp;
}

int quickselecta(vector<int>& A, int start, int end, int m) {
	
	int k, p;
	if (start < end) {
	  p = partitiona(A, start, end,m); // Line (*)
	k=p;
	//swap(A,p,start);
	/*
	cout << "Pivot is:" << A[p] << endl;
	for(int a = 0 ; a < A.size(); a ++) {
		cout << A[a] << " ";
	}
	cout << endl;
	*/
	
	
	if (m < k)
		return quickselecta(A, start, k-1, m);
	else if (m > k)
		return quickselecta(A, k+1, end, m-k);
	else
		return A[k];
	}
	
	else
		return A[start];
}

int quickselect(vector<int>& A, int start, int end, int m) {
	
	int k, p;
	if (start < end) {
	p = partition(A, start, end); // Line (*)
	k=p;
	//swap(A,p,start);
	/*
	cout << "Pivot is:" << A[p] << endl;
	for(int a = 0 ; a < A.size(); a ++) {
		cout << A[a] << " ";
	}
	cout << endl;
	*/
	
	
	if (m < k)
		return quickselect(A, start, k-1, m);
	else if (m > k)
		return quickselect(A, k+1, end, m-k);
	else
		return A[k];
	}
	
	else
		return A[start];
}

int main(int argc, char *argv[]) {

	char * mode = argv[1];
	vector<int> v;
	string line;
	ifstream file (argv[2]);
	int value, location;
	if (file.is_open())
	{	
		if (getline(file, line))
		{
		  istringstream iss(line);
		  while (iss >> value) {
		  v.push_back(value);
		  }
		}
		if (getline(file, line))
		{
		  istringstream iss(line);
		  while (iss >> value) {
		  location = value;
		  }
		}
	  file.close();
	}
	/*
	cout << "Information:" << endl;
	cout << mode << endl;
	
	for(int a = 0 ; a < v.size(); a ++) {
		cout << v[a] << " ";
	}
	cout << endl;
	
	cout << "Location: " << location << endl;
	*/

	if(strcmp(argv[1], "-alfredo") ==0) {
	  //cout<<"Alfredo"<<endl;
	  cout << quickselecta(v, 0, v.size()-1,location) << endl;
	cout << counta << endl;
	}
	else {
	  //cout<<"Normal"<<endl;
	cout << quickselect(v, 0, v.size()-1,location) << endl;
	cout << count << endl;
	}

	
	return 0;
 }
 
 
