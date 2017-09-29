package Problems026_050;

import utilities.Problems;

public class Problem030 {

	public static void main(String[] args) {
		String tempStr;
		long tempSum;
		long totalSum = 0;

		for (long i = 2; i < 200000; i++) { // 200000 is arbitrary, but it gets
											// the
			tempStr = "" + i; // right answer because the largest value is
								// 194979 (I checked all values <= 5m)
			tempSum = 0;
			for (int a = 0; a < tempStr.length(); a++) {
				tempSum += Math.pow(Integer.parseInt(String.valueOf(tempStr.charAt(a))), 5);
			}
			if (tempSum == i) {
				// System.out.println(i);
				totalSum += i;
			}
		}
		Problems.returnVal(totalSum, args);
		return;
	}
}
