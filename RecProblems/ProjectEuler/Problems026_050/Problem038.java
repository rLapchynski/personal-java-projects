package Problems026_050;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem038 {

	public static String concatonatedProduct = "";

	public static void main(String[] args) {
		long max = 0;
		for (int i = 0; i <= 100000; i++) {
			concatonatedProduct = "";
			for (int a = 1; concatonatedProduct.length() <= 9; a++) {
				concatonatedProduct += ((i * a) + "");
				if (Utils.isPandigital(Long.parseLong(concatonatedProduct))) {
					// System.out.println(concatonatedProduct + " " + i + " " +
					// a);
					if (Long.parseLong(concatonatedProduct) > max)
						max = Long.parseLong(concatonatedProduct);
				}
			}
		}
		Problems.returnVal(max, args);
		return;
	}

}