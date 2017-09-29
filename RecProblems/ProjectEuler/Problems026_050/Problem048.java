package Problems026_050;

import java.math.BigInteger;

public class Problem048 {

	public static void main(String[] args) {
		BigInteger sum = new BigInteger("0");
		for (int i = 1; i <= 1000; i++) {
			System.out.println(i);
			sum = sum.add(BigInteger.valueOf(i).pow(i));
		}
		System.out.println(sum.toString().substring(sum.toString().length() - 10));
	}

}
