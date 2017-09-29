package Problems001_025;

import java.util.ArrayList;
import java.util.Collections;

import utilities.*;

public class Problem024 {
	public static ArrayList<String> permutations = new ArrayList<String>();

	public static void main(String[] args) {
		int numToPrint = 1000000;
		permutation("0123456789");
		Collections.sort(permutations);
		// System.out.println(permutations.get(numToPrint-1));

		Problems.returnVal(Long.parseLong(permutations.get(numToPrint - 1)), args);
		return;
	}

	public static void permutation(String str) {
		permutation("", str);
	}

	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0)
			permutations.add(prefix);
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
		}
	}
}