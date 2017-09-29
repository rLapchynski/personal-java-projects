package Problems026_050;

import java.util.ArrayList;

import utilities.Data.Utils;

public class Problem047 {

	public static void main(String[] args) throws InterruptedException {
		// int a = 644;

		long b, occurances;
		for (int a = 134000; a < 1000000; a++) {

			if (!Utils.isPrime(a) && !Utils.isPrime(a + 1) && !Utils.isPrime(a + 2) && !Utils.isPrime(a + 3)) {
				System.out.println(a);
				System.out.println(".");
				if (Utils.listPowerDivisors(a + 0).size() == 4) {
					System.out.print(".");
					if (Utils.listPowerDivisors(a + 1).size() == 4) {
						System.out.print(".");
						if (Utils.listPowerDivisors(a + 2).size() == 4) {
							System.out.print(".");
							if (Utils.listPowerDivisors(a + 3).size() == 4) {
								System.out.print(".");
								ArrayList<Long> factors0 = Utils.listPowerDivisors(a + 0),
										factors1 = Utils.listPowerDivisors(a + 1),
										factors2 = Utils.listPowerDivisors(a + 2),
										factors3 = Utils.listPowerDivisors(a + 3), concatList = new ArrayList<Long>();

								concatList.addAll(factors0);
								concatList.addAll(factors1);
								concatList.addAll(factors2);
								concatList.addAll(factors3);

								// System.out.println(concatList);
								for (int i = 0; i < concatList.size(); i++) {
									b = concatList.get(i);
									occurances = Utils.countOccurance(concatList, b);
									if (occurances != 1) {
										System.out.println(a);
										try {
											Thread.sleep(10000);
										} catch (InterruptedException e) {
											// e.printStackTrace();
										}
										break;
									}
									if (i == concatList.size() - 1) {
										System.out.println(a + "					" + concatList);
										return;
									}
									// System.out.print(occurances + " ");
								}

							} else
								a += 3;
						} else
							a += 2;
					} else
						a += 1;
				}
			}
		}
	}

}