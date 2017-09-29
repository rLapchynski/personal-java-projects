package Problems001_025;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem010 {

	public static void main(String[] args) {
		answer = 0;
		long currPrime = Utils.primeN(0);
		while (currPrime < 2000000) {
			// System.out.println(currPrime);
			currPrime = Utils.nextPrime(currPrime);
			answer += currPrime;
		}
		Problems.returnVal(answer, args);
		return;
	}

	public static long answer;

}
