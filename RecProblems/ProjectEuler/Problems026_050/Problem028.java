package Problems026_050;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem028 {
	// This one involved more math than programming; see images of my whiteboard
	// on my phone for how I
	// worked out the equation for generating corner values.
	public static void main(String[] args) {
		long sum = 0;
		for (int i = 1; i < 2002; i++) {
			sum += Utils.ulamCorner((long) i);
		}
		Problems.returnVal(sum, args);
		return;
	}
}