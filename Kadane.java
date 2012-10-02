import java.util.ArrayList;
import java.util.Scanner;

public class Kadane {

	
	public static int kadane(ArrayList<Integer> l) {
		int maxSoFar = 0;
		int maxEndingHere = 0;
		
		for (Integer i : l) {
			maxEndingHere+=i;
			if(maxEndingHere < 0) 
				maxEndingHere = 0;
			if(maxSoFar < maxEndingHere)
				maxSoFar = maxEndingHere;
		}
		return maxSoFar;
		
	}
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> l = new ArrayList<Integer>();
		
		while(in.hasNextInt()) {
			l.add(in.nextInt());
		}
		
		System.out.println(kadane(l));
		
	}
	
}
