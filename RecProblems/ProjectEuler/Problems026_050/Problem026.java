package Problems026_050;

import java.math.BigDecimal;
import java.util.ArrayList;

import utilities.Problems;

public class Problem026 {

	public static void main(String[] args) {
		String recurringDecimal;
		// BigDecimal a = new BigDecimal(0);
		ArrayList<String> storeToCheck = new ArrayList<String>();
		String Checking = "";
		int recurringLength = 0;
		boolean recurringLengthFound = false;
		int largestRecurringLength = 0, lrlNum = 0;

		for (int i = 1; i < 1000; i++) {
			BigDecimal a = new BigDecimal("" + i);

			try {
				BigDecimal.ONE.divide(a);
			} catch (ArithmeticException e) {
				recurringDecimal = BigDecimal.ONE.divide(a, 5000, BigDecimal.ROUND_FLOOR).toString().substring(2);
				// System.out.println(recurringDecimal);

				for (int stringLength = 1; stringLength < recurringDecimal.length(); stringLength++) {
					storeToCheck.clear();
					for (int b = 0; b < recurringDecimal.length() / stringLength; b++) {
						if (b != 0)
							storeToCheck.add(recurringDecimal.substring(b * stringLength, (b + 1) * stringLength));
					}
					if (!(storeToCheck.size() <= 1)) {
						Checking = storeToCheck.get(0);
						recurringLength = 0;
						recurringLengthFound = true;
						for (int c = 1; c < storeToCheck.size(); c++) {
							if (!storeToCheck.get(c).equals(Checking)) {
								recurringLengthFound = false;
							}
						}
						if (recurringLengthFound) {
							recurringLength = Checking.length();
							if (recurringLength > largestRecurringLength) {
								largestRecurringLength = recurringLength;
								lrlNum = i;
							}
							// System.out.println("1/" + i + " Recurring Length:
							// " + recurringLength + "\n"
							// + BigDecimal.ONE.divide(a, 25,
							// BigDecimal.ROUND_FLOOR).toString());
							// System.out.println(storeToCheck + "\n" +
							// "-------------");
						}
					} else {
						storeToCheck.clear();
					}
					if (recurringLengthFound)
						break;

				}
			}
		}
		Problems.returnVal(lrlNum, args);
		return;
		// System.out.println("\n\n\n\n\n-------------------------\nRecurring
		// Sequence Length: "
		// + largestRecurringLength + "\n" + "Produced by: 1/" + lrlNum);
	}

}
