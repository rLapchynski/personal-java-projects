package Problems001_025;

import utilities.Problems;

public class Problem020 {

	public static void main(String[] args) {
		String number = "93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000";
		int total = 0;
		for (int i = 0; i < number.length(); i++) {
			total += Integer.parseInt(String.valueOf(number.charAt(i)));
		}
		Problems.returnVal(total, args);
		return;
	}
}
