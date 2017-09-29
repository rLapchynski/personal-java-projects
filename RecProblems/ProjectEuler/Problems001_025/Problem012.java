package Problems001_025;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem012 {

	public static void main(String[] args) {
		long answer = 1;
		long nextNum = 2;
		while (true) {
			if (Utils.countDivisors(answer) > 500) {
				break;
			} else {
				answer += nextNum;
				nextNum++;
			}
		}
		Problems.returnVal(answer, args);
		return;
	}
}