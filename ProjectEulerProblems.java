
public class ProjectEulerProblems {

	public static int Q1 () {
		int sum=0;
		for(int a = 0 ; a < 1000 ; a++) {
			if(a % 3 == 0 || a % 5 == 0) {
				sum+=a;
			}
		}
		return sum;
	}

	public static int Q2() {
		int a = 1, b = 2 , c, sum=2;
		while(true) {
			c=a+b;
			if(a>4000000)
				break;

			if(c % 2 == 0) {
				sum+=c;
			}
			a=b;
			b=c;

		}	
		return sum;
	}

	public static int Q4() {
		String str;
		Integer i;
		int ans=0;
		for(int a = 0 ; a < 1000 ; a++) {
			for(int b = 0 ; b < 1000 ; b++) {
				i = a*b;
				str = i.toString();
				for(int c = 0 ; c < (int)(str.length()/2); c++) {
					if(str.charAt(c) != str.charAt(str.length()-1-c))
						break;
					if(c == (int)(str.length()/2-1))
						if(i > ans)
							ans = i;
				}
			}
		}
		return ans;
	}

	public static int Q8() {
		String str = "73167176531330624919225119674426574742355349194934" +
				"96983520312774506326239578318016984801869478851843" +
				"85861560789112949495459501737958331952853208805511" +
				"12540698747158523863050715693290963295227443043557" +
				"66896648950445244523161731856403098711121722383113" +
				"62229893423380308135336276614282806444486645238749" +
				"30358907296290491560440772390713810515859307960866" +
				"70172427121883998797908792274921901699720888093776" +
				"65727333001053367881220235421809751254540594752243" +
				"52584907711670556013604839586446706324415722155397" +
				"53697817977846174064955149290862569321978468622482" +
				"83972241375657056057490261407972968652414535100474" +
				"82166370484403199890008895243450658541227588666881" +
				"16427171479924442928230863465674813919123162824586" +
				"17866458359124566529476545682848912883142607690042" +
				"24219022671055626321111109370544217506941658960408" +
				"07198403850962455444362981230987879927244284909188" +
				"84580156166097919133875499200524063689912560717606" +
				"05886116467109405077541002256983155200055935729725" +
				"71636269561882670428252483600823257530420752963450";
		int product=0;
		int ans=0;
		
		for(int a = 0 ; a < str.length()-5 ; a++) {
			product = Character.getNumericValue(str.charAt(a)) *
					Character.getNumericValue(str.charAt(a+1)) *
					Character.getNumericValue(str.charAt(a+2)) *
					Character.getNumericValue(str.charAt(a+3)) *
					Character.getNumericValue(str.charAt(a+4));
			if(product > ans)
				ans = product;
		}
		
		return ans;
	}
	
	public static int Q9() {
		for(int a = 1 ; a < 1000 ; a++) {
			for(int b = a ; b < 1000 ; b++) {
				if(a+b+Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2)) == 1000 && Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2))>b && Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2))>a) {
					return a*b*(int)Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2)); 
				}
			}
		}
		return -1;
	}
	
	
	
	public static void main (String args[]) {
		System.out.println(Q9());
	}

}
