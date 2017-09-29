package Problems026_050;

import utilities.Data.Utils;

public class Problem044 {
	public static void main(String[] args) {
		// 1, 5, 12, 22, 35, 51, 70, 92, 117, 145
		long pent1, pent2;
		for (int i = 1; i < 10000; i++) {
			for (int a = 1; a < 10000; a++) {
				pent1 = Utils.pentagonalNum(i) + Utils.pentagonalNum(a);
				pent2 = Utils.pentagonalNum(a) - Utils.pentagonalNum(i);
				if (Utils.isPentagonal(pent1) || Utils.isPentagonal(pent2))
					System.out.println(Utils.pentagonalNum(i) + " " + Utils.pentagonalNum(a));
				if (Utils.isPentagonal(pent1) && Utils.isPentagonal(pent2)) {
					System.out.println(Utils.pentagonalNum(i) + " " + Utils.pentagonalNum(a) + " "
							+ (Utils.pentagonalNum(a) - Utils.pentagonalNum(i)));
					return;
				}
			}
		}
	}

}