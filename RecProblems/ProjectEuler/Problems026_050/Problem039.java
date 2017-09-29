package Problems026_050;

import utilities.Data;
import utilities.Problems;

public class Problem039 {

	public static void main(String[] args) {
		int max = 0;
		int maxNumSolutions = 0;
		for (int i = 0; i <= 1000; i++) {
			int numSolutions = 0;

			for (String triple : Data.Utils.pythagoreans(500)) {
				if (Integer.parseInt(triple.split(",")[0]) + Integer.parseInt(triple.split(",")[1])
						+ Integer.parseInt(triple.split(",")[2]) == i) {
					numSolutions++;
				}
			}

			if (numSolutions > maxNumSolutions) {
				maxNumSolutions = numSolutions;
				max = i;
			}
		}
		Problems.returnVal(max, args);
		return;
	}
}