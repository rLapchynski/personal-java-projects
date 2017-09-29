package Problems026_050;

import utilities.Data.Utils;

public class Problem045 {

	public static void main(String[] args) {
		long a;
		for (long i = 2; true; i++) {
			a = (i * (i + 1)) / 2;
			if (Utils.isPentagonal(a) && Utils.isHexagonal(a)) {
				if (a > 40755) {
					System.out.println(a);
					break;
				}
			}
		}
	}

}
