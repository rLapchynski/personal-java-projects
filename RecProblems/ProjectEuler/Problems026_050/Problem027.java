package Problems026_050;

import utilities.Data.Utils;
import utilities.Problems;

public class Problem027 {

	public static void main(String[] args) {
		int currentMaxN = 0, n, min = -1000, max = 1000, maxA = 0, maxB = 0;
		boolean eval;

		for (int a = min; a < max; a++) {
			for (int b = min; b < max; b++) {
				n = 0;
				eval = true;
				// System.out.println("n^2 + " + a + " * n + " + b);
				while (eval) {
					double num = Math.pow(n, 2) + (a * n) + b;
					eval = Utils.isPrime((long) num);
					// System.out.println(n + " " + num + " " +
					// Num.isPrime((long)num));
					if (n > currentMaxN) {
						currentMaxN = n;
						maxA = a;
						maxB = b;
					}
					n++;
				}
				// System.out.print("Prime Sequence Length: " + (n-1));
				// System.out.println("\n--------------------------\n");
				// n² + an + b
			}
		}

		// System.out.println("n" + "^2 + " + maxA + " * " + "n" + " + " + maxB
		// + " Generates " + currentMaxN + " primes");
		// System.out.println("Product of Coeffieients: " + maxA * maxB);
		Problems.returnVal(maxA * maxB, args);
		return;
	}

}
