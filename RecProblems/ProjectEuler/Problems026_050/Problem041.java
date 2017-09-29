package Problems026_050;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem041 {

	public static void main(String[] args) {
		int max = 0;
		for (int i = 6; i < 7654321; i += 6) {
			if (Utils.isPrime((long) i - 1) && Utils.isNdPandigital((long) i - 1) && i - 1 > max)
				max = i - 1;
			if (Utils.isPrime((long) i + 1) && Utils.isNdPandigital((long) i + 1) && i + 1 > max)
				max = i + 1;

		}
		Problems.returnVal(max, args);
		return;

	}
}