package Problems001_025;

import utilities.Problems;

public class Problem001 {
	public static long answer;

	public static void main(String[] args) {
		answer = 0;
		for (int i = 0; i < 1000; i++) {
			if (i % 5 == 0 || i % 3 == 0) {
				answer += i;
			}
		}
		Problems.returnVal(answer, args);
		return;
	}
}
