package ProblemsC001_025;

import java.util.Scanner;

public class Problem004 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print(" ");
		
	    String[] input = new String[Integer.parseInt(sc.nextLine())];
		
		for(int i = 0 ; i < input.length; i++) {
			input[i] =  sc.nextLine();
		}
		
		String a = "";
		
		for(String i : input) {
			if(i.equals("")) continue;
			a += ( (Integer.parseInt(i.split(" ")[0])) < (Integer.parseInt(i.split(" ")[1])) ? (Integer.parseInt(i.split(" ")[0])) : (Integer.parseInt(i.split(" ")[1])) )  + " ";
		}
		a = a.substring(0, a.length()-1);
		
		System.out.println(a);
		
		sc.close();
	}

}