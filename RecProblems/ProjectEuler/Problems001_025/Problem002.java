package Problems001_025;

import utilities.Problems;

public class Problem002 {
	// private static double total = 0;
	private static double previousValue = 0;
	private static double currentValue = 1;

	public static void main(String[] args) {
		answer = 0;
		while (currentValue <= 4000000) {
			if (currentValue % 2 == 0) {
				answer += currentValue;
			}
			currentValue += previousValue;
			previousValue = currentValue - previousValue;
			// System.out.println("\n" + currentValue);
			// System.out.println(total);
		}
		Problems.returnVal(answer, args);
		return;
	}

	public static long answer;

}