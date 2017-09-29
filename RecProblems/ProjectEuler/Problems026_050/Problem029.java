package Problems026_050;

import java.math.BigInteger;
import java.util.ArrayList;

import utilities.Problems;

public class Problem029 {

	public static void main(String[] args) {
		ArrayList<BigInteger> terms = new ArrayList<BigInteger>();
		BigInteger temp;
		for (int a = 2; a <= 100; a++) {
			for (int b = 2; b <= 100; b++) {
				temp = BigInteger.valueOf(a).pow(b);
				if (!terms.contains(temp))
					terms.add(temp);
			}
		}
		Problems.returnVal(terms.size(), args);
		return;
	}
}
