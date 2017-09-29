package Problems026_050;

import java.math.BigInteger;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem036 {

	public static void main(String[] args) {
		BigInteger binVal = new BigInteger("0");
		long sum = 0;
		for (long i = 0; i < 1000000; i++) {
			binVal = BigInteger.valueOf(i);
			binVal = new BigInteger(binVal.toString(2));
			if (Utils.isPalindrome(binVal) && Utils.isPalindrome(i)) {
				// System.out.println(i + " " + binVal);
				sum += i;
			}
			// System.out.println(i + " " + binVal + " " + Num.isPalindrome(i) +
			// " " + Num.isPalindrome(binVal));
		}
		Problems.returnVal(sum, args);
		return;
	}

}
