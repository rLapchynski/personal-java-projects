package Problems001_025;

import utilities.Problems;

public class Problem006 {
	private static long squareSum = 0; // Line1
	private static long sumSquare = 0; // Line2

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			squareSum += Math.pow(i, 2);
			sumSquare += i;
		}
		answer = (long) Math.pow(sumSquare, 2) - squareSum;
		Problems.returnVal(answer, args);
		return;
	}

	public static long answer;
}
