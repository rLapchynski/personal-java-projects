package Problems026_050;

import utilities.Data.Utils;

public class Problem046 {

	public static void main(String[] args) {
		for (int i = 3; i < 10000; i += 2) {
			if (!Utils.isPrime(i))
				try {
					if (!Utils.canBeWrittenAs(i, Utils.Forms.SUM_OF_PRIME_AND_2SQUARE)) {
						System.out.println(i);
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
