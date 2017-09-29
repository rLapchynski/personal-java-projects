package Problems026_050;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem034 {

	public static void main(String[] args) throws Exception {
		long sum = 0;
		for (int i = 3; i < 1000000; i++) {
			if (Utils.sumOfDigitFactorial(i) == i) {
				sum += i;
				// System.out.println(i);
			}
		}
		Problems.returnVal(sum, args);
		return;
	}

}
