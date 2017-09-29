package stackOverflowStuff;

import java.io.*;
import java.util.*;

public class Echo {

	public static void main(String[] args) throws FileNotFoundException {
	
	    Scanner in = new Scanner(new FileInputStream(args[0]));
	    Random rng = new Random();
	    
	    int size = in.nextInt();					//Read the first line for the number of words
	    in.nextLine(); 								//Because nextInt doesn't move to the next line, and the for loop has to start on the first line with something on it.
	    String[] lines = new String[size];			//Create an array of Strings to hold the words
	    
	    for(int i=0; i<size; i++){					//Read in the lines from the file into the array
	    	lines[i] = in.nextLine();
	    }
	    
	    System.out.println(lines[rng.nextInt(size)]);		//Pick a random index from the array and display it. This can be put in a loop if multiple outputs are required.
	    
	    											//THIS IS NOT FAULT TOLERANT; IF THE FILE CONTAINS FEWER LINES THAN ARE SPECIFIED IN THE FIRST LINE, AN ERROR WILL BE THROWN.
	    in.close();
	
	}
}