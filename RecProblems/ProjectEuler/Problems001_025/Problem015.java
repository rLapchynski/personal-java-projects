package Problems001_025;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem015 {
	public static void main(String[] args) throws Exception {
		Problems.returnVal(binomialCoefficient(40, 20), args);
		return;
	}

	public static long binomialCoefficient(int n, int k) throws Exception {
		return (long) (Utils.factorial(n) / (Utils.factorial(k) * Utils.factorial(n - k)));
	}
}