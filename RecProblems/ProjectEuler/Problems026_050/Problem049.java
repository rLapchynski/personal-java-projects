package Problems026_050;

import java.util.ArrayList;
import java.util.Collections;

import utilities.Data.Utils;

public class Problem049 {

	public static void main(String[] args) {
		ArrayList<Long> permutations;
		// System.out.println(Num.permutations(1063));
		for (int i = 1486; i < 9999; i++) {
			permutations = Utils.permutation(i);
			Collections.sort(permutations);
			// if(permutations.get(0) < i) continue;
			for (long a : permutations) {
				for (long b : permutations) {
					if (b >= a)
						break;
					for (long c : permutations) {
						if (c >= b)
							break;
						if (a - b == b - c && Utils.isPrime(a) && Utils.isPrime(b) && Utils.isPrime(c) && a >= 1000
								&& b >= 1000 && c >= 1000 && a != 1487 && b != 1487 && c != 1487) {
							System.out.println(c + " " + b + " " + a);
							// System.out.println(":" + (a-b) + " " + (b-c));
							return;
						}
					}
				}
			}

		}

	}

}
