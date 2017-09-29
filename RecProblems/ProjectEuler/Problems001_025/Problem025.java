package Problems001_025;

import java.math.BigInteger;

import utilities.Problems;

public class Problem025 {

	public static BigInteger previousValue = new BigInteger("0");
	public static BigInteger currentValue = new BigInteger("1");

	public static void main(String[] args) {

		long index = 1;

		while (currentValue.toString().length() < 1000) {
			currentValue = currentValue.add(previousValue);
			previousValue = currentValue.subtract(previousValue);
			index++;
			// if(index%1000==0)System.out.println(index);
		}

		// System.out.println(index + " " + currentValue.toString().length() + "
		// " + currentValue );
		Problems.returnVal(index, args);
		return;
	}

}
