package stackOverflowStuff;

import java.util.*;

public class Whatever {

	public static void main(String[] args) {
		double amount;
		boolean amountaccept = false;
		
		Scanner k = new Scanner(System.in);

		do {

		  System.out.print("How much money do you want to borrow? \n");
		  amount = k.nextDouble();
		  if (amount > 10000) {

		    amountaccept = true;
		  } else {

		    System.out.println("I'm afraid banks don't give mortgage loans of less than $10,000.00. ");
		  }

		}
		while (!amountaccept);
		
		k.close();

	}

}
