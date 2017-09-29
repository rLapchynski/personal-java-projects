package Problems001_025;

import java.math.BigInteger;
import java.util.ArrayList;

import utilities.Files;
import utilities.Problems;

public class Problem013 {
	public static ArrayList<String> numbers = new ArrayList<String>();

	public static void main(String[] args) {
		numbers = Files.fileList(System.getProperty("user.dir") + "/bin/Problem013_Numbers.txt");
		BigInteger currentTotal = new BigInteger("0");
		for (int i = 0; i < 100; i++) {
			currentTotal = currentTotal.add(new BigInteger(numbers.get(i)));
		}
		Problems.returnVal(Long.parseLong(currentTotal.toString().substring(0, 11)), args);
		return;

	}
}