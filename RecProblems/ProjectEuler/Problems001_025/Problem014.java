package Problems001_025;

import utilities.Problems;

public class Problem014 {
	public static int currStarting = 2;
	public static long currLargest = 0;
	public static double currNumber = 2;
	public static int currMaxSteps = 0;
	public static boolean contLoop = true;
	public static boolean contLoop0 = true;

	public static void main(String[] args) {
		while (contLoop) {
			int numSteps = 0;
			contLoop0 = true;
			currNumber = currStarting;
			while (contLoop0) {
				// System.out.print(currNumber + ", ");
				if (currNumber % 2 == 0.0) {
					currNumber /= 2;
				} else {
					currNumber = (currNumber * 3) + 1;
				}
				numSteps++;
				if (currNumber == 1.0) {
					contLoop0 = false;
				}
			}
			// System.out.println(currStarting + " " + numSteps);
			if (numSteps > currMaxSteps) {
				currLargest = currStarting;
				currMaxSteps = numSteps;
			}
			if (currStarting >= 1000000) {
				contLoop = false;
			}
			currStarting++;
		}
		Problems.returnVal(currLargest, args);
		return;
	}

}
