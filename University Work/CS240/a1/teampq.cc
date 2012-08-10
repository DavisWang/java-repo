/**
 * by Davis Wang 
 * CS240 assignment 1 question 6b
**/

#include <iostream>
#include <string>
#include <sstream>
#include  <fstream>

using namespace std;

class Team {
public:
	int wins;
	int losses;
	string name;
	int minArrIndex; //the index this team is on in the min heap
	int maxArrIndex; //the index this team is on in the max heap
	// You may add fields/methods/constructors/destructor as necessary
};

class TeamPQ {
	// add fields/methods/constructors/destructor as necessary
	
	private:
	
	void siftUp(int index, Team array[], bool max);
	void siftDown(int index, Team array[], bool max);
	void swapIndices(int parentIndex, int index, Team array[]); //swaps two max/min heap indices
	
	public:
	TeamPQ();
	Team mostWins [100000];
	Team leastLosses [100000];
	int heapSize;
	void insert(const Team &t); // O(log n) time
	const Team &findMaxWins() const; // O(1) time
	const Team &findMinLosses() const; // O(1) time
	void removeMaxWins(); // O(log n) time
	void removeMinLosses(); // O(log n) time
};

//swaps the minArrIndex and the maxArrIndex for the teams at parentIndex and index
void TeamPQ::swapIndices (int parentIndex, int index, Team array[]) {
	int tempMinIndex,tempMaxIndex;
	
 	tempMinIndex= array[parentIndex].minArrIndex;
	array[parentIndex].minArrIndex = array[index].minArrIndex;
	array[index].minArrIndex = tempMinIndex;
			
	tempMaxIndex = array[parentIndex].maxArrIndex;
	array[parentIndex].maxArrIndex = array[index].maxArrIndex;
	array[index].maxArrIndex = tempMaxIndex;
}

TeamPQ::TeamPQ(){
	heapSize=0;
	}

const Team& TeamPQ::findMaxWins () const {

	return mostWins[0];
}

const Team& TeamPQ::findMinLosses () const {
	return leastLosses[0];
}



void TeamPQ::siftUp(int index, Team array[], bool max) {
	int parentIndex;
	Team temp;
	
	if(index != 0) {
		if(index % 2 == 0)
			parentIndex = (int)((index/2)-1);
		else 
			parentIndex =  (int)(index/2);
		
		if(!max && array[parentIndex].losses > array[index].losses) { //if this is a min heap, and the child has less losses, then swap
			//cout << "Swapping " << parentIndex << " " << index<<endl;
			temp = array[parentIndex];
			array[parentIndex] = array[index];
			array[index] = temp;
			
			swapIndices(parentIndex, index, array);
			
			siftUp(parentIndex, array, max);
			
		}
		else if(max && array[parentIndex].wins < array[index].wins) { //if this is a max heap, and the child has more wins, then swap
			temp = array[parentIndex];
			array[parentIndex] = array[index];
			array[index] = temp;
			
			swapIndices(parentIndex, index, array);
			
			siftUp(parentIndex, array, max);
		}	
	}
}

void TeamPQ::siftDown(int index, Team array[], bool max) {
	int leftIndex = index*2+1, rightIndex = index*2+2, min;
	Team temp;
	int tempMinIndex, tempMaxIndex;
	
	
	if(!max) { //min losses array
		
		if(rightIndex >= heapSize) //dont do anything if there are no children for the given index
			if(leftIndex >= heapSize)
				return;
		if(array[leftIndex].losses >= array[index].losses && array[rightIndex].losses >= array[index].losses) return;
	
		else if(array[leftIndex].losses < array[rightIndex].losses) { //leftindex has least losses, swap parent with left child
		temp = array[index];
		array[index] =  array[leftIndex];
		array[leftIndex] = temp;
		swapIndices(index, leftIndex, array);
		
		siftDown(leftIndex, array, max);
		
		}
		else { //right index has least losses, swap parent with right child
		temp = array[index];
		array[index] =  array[rightIndex];
		array[rightIndex] = temp;
		
		swapIndices(index, leftIndex, array);
		
		siftDown(rightIndex, array, max);
		}	
	}
	
	else { //max wins array
	
		if(rightIndex >= heapSize)
			if(leftIndex >= heapSize)
				return;
	
		if(array[leftIndex].wins <= array[index].wins && array[rightIndex].wins <= array[index].wins) return;
		
		else if (array[leftIndex].wins > array[rightIndex].wins) { //leftIndex has more wins, swap parent with left child
		temp = array[index];
		array[index] =  array[leftIndex];
		array[leftIndex] = temp;
		
		swapIndices(leftIndex, index, array);
		
		siftDown(leftIndex, array, max);
		}
		
		else {//right index has more wins, swap parent with right child.
		temp = array[index];
		array[index] =  array[rightIndex];
		array[rightIndex] = temp;
		
		swapIndices(rightIndex, index, array);
		
		siftDown(rightIndex, array, max);
		}
	}
}

//insert team t at heapSize of the heap, then siftUp in both heaps.
void TeamPQ::insert(const Team &t) {

	mostWins[heapSize] = t;
	leastLosses[heapSize] = t;

	siftUp(heapSize, mostWins, true);
	siftUp(heapSize, leastLosses, false);
	
	//cout<< t.minArrIndex<< t.maxArrIndex<<endl;
	
	heapSize++;
	
}

//swap the 0th element in the max win heap, then find the index of the element in the min losses heap, then swap it with the last element
//and sift that swapped element down.
void TeamPQ::removeMaxWins() {

	if(heapSize == 0)
	return;

	int index = mostWins[0].minArrIndex;
	mostWins[0] = mostWins[heapSize-1];
	siftDown(0, mostWins, true);
	
	leastLosses[index] = leastLosses[heapSize-1];
	siftDown(index, leastLosses, false);
	heapSize--;
	
	
}
//same logic as removeMaxWins, except it's the other way around.
void TeamPQ::removeMinLosses() {
	if(heapSize == 0)
	return;

	int index = leastLosses[0].maxArrIndex;
	
	leastLosses[0] = leastLosses[heapSize -1];
	
	siftDown(0, leastLosses, false);

	mostWins[index] = mostWins[heapSize-1];
	
	siftDown(index, mostWins, true);
	
	heapSize--;
}



int main ()
{
	string s; //the string in a line
	istringstream instream;
	string oper;//operator
	
	TeamPQ pq;

	while(getline(cin, s))
	{
		instream.clear();
		instream.str(s);
		instream >> oper;

		if(oper.compare("i")==0)
		{
			Team team;
			instream >> team.wins;
			instream >> team.losses;
			instream >> team.name;			
			team.minArrIndex = pq.heapSize;
			team.maxArrIndex = pq.heapSize;
			//insert into pq
			//cout<< "--------------------" <<pq.heapSize<<endl;
			//cout<< "--------------------" <<team.minArrIndex << team.maxArrIndex<<endl;

			pq.insert(team);
			
		}
		else if(oper.compare("pw")==0)	
		{
			cout << pq.findMaxWins().name << endl;
		}
		else if(oper.compare("pl")==0)
		{
			cout << pq.findMinLosses().name << endl;
		}
		
		else if (oper.compare("rw")==0)	
		{
			pq.removeMaxWins();
		}
		
		else if (oper.compare("rl")==0)	
		{
			pq.removeMinLosses();
		}
		
	}

	return 0;
}
