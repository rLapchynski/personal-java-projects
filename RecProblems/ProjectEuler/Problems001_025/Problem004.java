package Problems001_025;

import utilities.Problems;

public class Problem004 {

	public static void main(String[] args) {
		for (int i = 999; i > 899; i--) {
			for (int a = 999; a > 899; a--) {
				if (Integer.parseInt(new StringBuilder((i * a) + "").reverse().toString()) == a * i) {
					Problems.returnVal(a * i, args);
					return;
				}
			}
		}
	}
}