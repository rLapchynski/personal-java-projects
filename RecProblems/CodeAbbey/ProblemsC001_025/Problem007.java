package ProblemsC001_025;

import java.util.Scanner;

public class Problem007 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("");
		
		String[] input = sc.nextLine().split(" ");
		double cTemp;
		
		for(int i = 1; i <= Integer.parseInt(input[0]); i++) {
			cTemp = ((Integer.parseInt(input[i]) - 32)*5)/9.0;
			System.out.print((int)(cTemp + (cTemp < 0 ? -.5 : .5)) + " ");
		}
		
		sc.close();
	}

}
