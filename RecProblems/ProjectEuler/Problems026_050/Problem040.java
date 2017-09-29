package Problems026_050;

import utilities.Problems;

public class Problem040 {
	public static String intList = "";

	public static void main(String[] args) {
		long index = 1;
		int product = 1;
		for (long i = 1; index < 1000000; i++) {
			for (int a = 0; a < (i + "").length(); a++) {
				if (index == 1 || index == 10 || index == 100 || index == 1000 || index == 10000 || index == 100000
						|| index == 1000000)
					product *= Integer.parseInt((i + "").substring(a, a + 1));
				index++;
			}
		}
		Problems.returnVal(product, args);
		return;
	}
}