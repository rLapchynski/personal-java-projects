package Problems026_050;

import utilities.Problems;

public class Problem033 {

	public static void main(String[] args) {
		double value = 0;
		long denominator = 1, numerator = 1;
		for (double i = 11; i < 100; i++) {
			for (double a = 11; a < 100; a++) {
				if (i < a && !((a % 11 == 0) && (i % 11 == 0)) && !(a % 10 == 0) && !(i % 10 == 0)) {
					value = i / a;
					if (((((i % 10) / (a % 10) == value) && ((i - (i % 10)) / 10) == ((a - (a % 10)) / 10)))
							|| ((((i - (i % 10)) / 10) / (a % 10) == value) && (i % 10) == ((a - (a % 10)) / 10))
							|| (((i % 10) / ((a - (a % 10)) / 10) == value) && ((i - (i % 10)) / 10) == (a % 10))
							|| ((((i - (i % 10)) / 10) / ((a - (a % 10)) / 10) == value) && (i % 10) == (a % 10))) {
						denominator *= a;
						numerator *= i;
					}
				}
			}
		}
		// The final fraction is 387296/38729600 which simplifies to 1/100
		Problems.returnVal((long) ((numerator / (double) denominator) * 10000), args);
		return;
	}
}