package Problems026_050;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem035 {

	public static void main(String[] args) {
		long sum = 0;
		boolean isCircular;
		for (int i = 2; i < 1000000; i++) {
			isCircular = true;
			for (long value : Utils.cycleDigits((long) i)) {
				if (!Utils.isPrime(value)) {
					isCircular = false;
					break;
				}
			}
			if (isCircular) {
				// System.out.println(i);
				sum++;
			}
			if (i % 10000 == 0)
				System.out.println(i);
		}
		Problems.returnVal(sum, args);
		return;
	}

}
