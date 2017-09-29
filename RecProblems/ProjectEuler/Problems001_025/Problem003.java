package Problems001_025;

import utilities.Problems;

public class Problem003 {
	// private static double number = 600851475143.0;
	public static void main(String[] args) {
		answer = (long) 600851475143.0;
		for (double divisor = 2; divisor <= answer / 2.0; divisor++) {
			if (answer % divisor == 0) {
				answer /= divisor;
			}
		}

		Problems.returnVal(answer, args);
		return;
	}

	public static long answer;

}