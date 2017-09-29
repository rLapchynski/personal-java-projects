package Problems001_025;

import utilities.Problems;

public class Problem009 {

	public static long answer;

	public static void main(String[] args) {
		for (int a = 1; a < 1000; a++) {
			for (int b = 1; b < 1000; b++) {
				for (int c = 1; c < 1000; c++) {
					if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
						if (a + b + c == 1000) {
							answer = a * b * c;
							Problems.returnVal(answer, args);
							return;
						}
					}
				}
			}
		}
	}

}