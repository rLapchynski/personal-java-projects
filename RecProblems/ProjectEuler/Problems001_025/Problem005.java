package Problems001_025;

import utilities.Problems;

public class Problem005 {

	public static void main(String[] args) {
		for (long numToTest = 2520; numToTest <= 2.5E18; numToTest++) {
			for (int divisorTest = 1; divisorTest <= 20; divisorTest++) {
				if (numToTest % divisorTest != 0) {
					break;
				} else {
					if (divisorTest == 20) {

						Problems.returnVal(numToTest, args);
						return;
					}
				}
			}
		}
	}

}
