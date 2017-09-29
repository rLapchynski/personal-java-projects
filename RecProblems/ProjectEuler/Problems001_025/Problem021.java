package Problems001_025;

import utilities.Problems;

public class Problem021 {
	// https://oeis.org/A259180
	// Amicable Pairs: 220, 284, 1184, 1210, 2620, 2924, 5020, 5564, 6232, 6368,
	// 10744,
	// 10856, 12285, 14595, 17296, 18416, 63020, 76084, 66928, 66992, 67095,
	// 71145,
	// 69615, 87633, 79750, 88730, 100485, 124155, 122265, 139815, 122368,
	// 123152, 141664,
	// 153176, 142310, 168730, 171856, 176336, 176272, 180848, 185368, 203432,
	// 196724, 202444, 280540, 365084
	public static void main(String[] args) {
		int b;
		int sum = 0;
		for (int a = 0; a < 10000; a++) {
			b = sumFactors(a);
			if (a == sumFactors(b) && a != b) {
				sum += a;
			}

		}

		Problems.returnVal(sum, args);
		return;
	}

	private static int sumFactors(int n) {
		int sum = 0;
		for (int div = 1; div < Math.ceil(n / 2.0) + 1; div++) {
			if (n % div == 0)
				sum += div;
		}
		return sum;
	}
}
