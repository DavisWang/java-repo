/**
	NAIVE SOLUTION TO LONGEST REPEATING SUBSTRING PROBLEM
	By Davis Wang

	USE: A naive solution to the longest repeating substring problem, it is a n^3 algorithm.

	USAGE: java -jar HttpStatusCodeChecker.jar [option] <string>

	EXAMPLE USAGE:
		java -jar HttpStatusCodeChecker.jar --file file.txt
		java -jar StringPattern.jar aabbabbabcacb

	OPTIONS:
		-f or --file : takes input from a file, supplied as a relative path to the current jar directory.

**/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class StringTest
{
  public static void main(String[] args)
    throws IOException
  {
    if ((args[0].equals("--file")) || (args[0].equals("-f"))) {
      File file = new File(args[1]);
      BufferedReader br = new BufferedReader(new FileReader(file));
      String str;
      while ((str = br.readLine()) != null)
      {
        String str;
        System.out.println(getLongestPattern(str));
      }
    }
    else {
      System.out.println(getLongestPattern(args[0]));
    }
  }

  public static String getLongestPattern(String str) {
    String finalString = "";
    for (int a = 0; a <= str.length(); a++) {
      for (int b = a + 1; b <= str.length(); b++) {
        String sub = str.substring(a, b);
        String strLeft = str.substring(a + sub.length(), str.length());
        if (strLeft.length() < sub.length())
          break;
        if ((!strLeft.contains(sub)) || 
          (sub.length() <= finalString.length())) continue;
        finalString = sub;
      }
    }

    return finalString;
  }
}