package Problems051_075;

import java.util.ArrayList;

import utilities.Data.Utils;

public class Problem051 {

	public static void main(String[] args) {
		ArrayList<String> listOfStuff = new ArrayList<String>();
		int c, maxC = 0, prevK = 0;

		mainLoop: for (int i = 100001; true; i += 2) {
			if (i % 2 == 0 || i % 3 == 0 || i % 5 == 0 || i % 7 == 0)
				continue;
			if (i - (i % 10000) != prevK) {
				listOfStuff.clear();
				System.out.println("------" + (i - (i % 10000)));
			}
			if (Math.abs(i % 500) < 2)
				System.out.println(":" + i);
			prevK = i - (i % 10000);
			for (String a : Utils.replaceEachCharacter("" + i)) {
				if (!listOfStuff.contains(a))
					listOfStuff.add(a);
				else
					continue;
				if (a.substring(a.length() - 1).equals("*"))
					continue;
				c = 0;
				for (long b : Utils.replaceStars(a)) {
					if (Utils.isPrime(b) && Utils.length(b) == Utils.length(i)) {
						c++;
					}
				}
				if (c > 5)
					System.out.println(i + " " + a + " " + c);
				if (c > 7) {
					for (long b : Utils.replaceStars(a)) {
						if (Utils.isPrime(b)) {
							System.out.println("	" + b);
						}
					}
					break mainLoop;
				}
				if (c > maxC) {
					maxC = c;
				}
			}
		}
	}

}
