package Problems026_050;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem037 {

	public static void main(String[] args) {

		long answer = 0;

		for (int i = 0; i < 100008; i++) {
			if (Utils.isTruncatablePrime(Utils.primeN(i)) && i > 4) {
				answer += Utils.primeN(i);
			}
		}

		Problems.returnVal(answer, args);
		return;
	}

}
