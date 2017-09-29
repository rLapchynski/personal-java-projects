package ProblemsC001_025;

import java.util.*;

public class Problem001 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    
		String[] input = sc.nextLine().split(" ");
		
		System.out.println(Integer.parseInt(input[0]) + Integer.parseInt(input[1]));
		
		sc.close();
	}

}
