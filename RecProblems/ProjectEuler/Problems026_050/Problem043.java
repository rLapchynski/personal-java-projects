package Problems026_050;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem043 {

	public static void main(String[] args) {

		long num, total = 0;
		int i;
		boolean testB;
		for (String tmpStr : Utils.permutation("0123456789")) {
			num = Long.parseLong(tmpStr);
			if (!(num > 1000000000))
				continue;
			if (tmpStr.length() == 9)
				tmpStr = "0" + tmpStr;

			if (Utils.isNdPandigital0(Long.parseLong(tmpStr), true, false)) {
				testB = true;
				for (i = 1; i < 8; i++) {
					if (Integer.parseInt(tmpStr.substring(i, i + 3)) % Utils.primeN(i - 1) != 0) {
						testB = false;
						break;
					}
				}
				if (testB) {
					// System.out.println(num);
					total += num;
				}
			}
		}
		Problems.returnVal(total, args);
		return;
	}
}