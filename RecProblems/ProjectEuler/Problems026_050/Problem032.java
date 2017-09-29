package Problems026_050;

import java.util.Arrays;

import utilities.Problems;

public class Problem032 {

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i < 10000; i++) {
			for (int a = 1; a <= i; a++) {
				if (i % a == 0 && isPandigital("" + i + a + i / a)) {
					sum += i;
					break;
				}
			}
		}

		Problems.returnVal(sum, args);
		return;
	}

	private static boolean isPandigital(String s) {
		if (s.length() != 9)
			return false;
		char[] temp = s.toCharArray();
		Arrays.sort(temp);
		return new String(temp).equals("123456789");
	}

}
