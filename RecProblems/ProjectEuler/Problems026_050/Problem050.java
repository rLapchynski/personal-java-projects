package Problems026_050;

import utilities.Data.Utils;

public class Problem050 {

	public static void main(String[] args) {
		int maxLen = 0, len, sum;
		loop1: for (int i = 0; i < 1000000; i = Utils.nextPrime(i).intValue()) {
			sum = 0;
			for (int a = i; sum < 1000000; a = Utils.nextPrime(a).intValue()) {
				sum += a;
				if (sum > 1000000 && Utils.isPrime(sum - a)) {
					sum -= a;
					len = Utils.listPrimesBetween(i, a).size();
					if (len > maxLen) {
						maxLen = len;
						System.out.println(i + " " + Utils.prevPrime(a) + " " + sum + " " + len);
					}
					break loop1;
				}
			}
		}
	}

}
