package Problems001_025;

import utilities.Problems;

public class Problem019 {

	public static void main(String[] args) {
		int numOfSundaysOnTheFirst = 0;
		int weekDay = 1; // Monday, Tuesday, Wednesday, Thursday, Friday,
							// Saturday, Sunday: 0,1,2,3,4,5,6
		int month = 0; // Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Nov, Dec:
						// 0,1,2,3,4,5,6,7,8,9,10,11
		int dayOfMonth = 1;
		int[] monthLengths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = 1900;
		while (year <= 2000) {

			if (year % 4 == 0 && !(year % 400 == 0 && year % 1000 == 0))
				monthLengths[1] = 29;
			else
				monthLengths[1] = 28;

			if (weekDay == 6)
				weekDay = 0;
			else
				weekDay++;

			if (dayOfMonth > monthLengths[month]) {
				month++;
				dayOfMonth = 1;
			} else {
				dayOfMonth++;
			}

			if (month > 11) {
				year++;
				month = 0;
			}

			// System.out.println("Weekday: " + weekDay + " " + dayOfMonth + "/"
			// + month + "/" + year);

			if (weekDay == 6 && dayOfMonth == 1)
				numOfSundaysOnTheFirst++;
		}

		Problems.returnVal(numOfSundaysOnTheFirst, args);
		return;
	}

}
