package ProblemsC001_025;

import java.util.Scanner;

public class Problem002 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    
		sc.nextLine();
		String[] input = sc.nextLine().split(" ");
		
		int a = 0;
		
		for(String i : input) {
			a += Integer.parseInt(i);
		}
		
		System.out.println(a);
		
		sc.close();
	}

}
