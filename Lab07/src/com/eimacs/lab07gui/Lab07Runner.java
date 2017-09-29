package com.eimacs.lab07gui;

import com.eimacs.lab07.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author IMACS Curriculum Development Group
 * @version 2.0 January 2015
 */
public class Lab07Runner
{
	public static int random( int n ) 
	{ 
	  return (int)(n * Math.random()); 
	} 
	
	public static ArrayList<Integer> randomArrayList( int n, int dir ){
		ArrayList<Integer> output = new ArrayList<Integer>();
	    for(int i = 0; i < n; i++){
	        output.add((random(10) * 100) + (random(10) * 10) + random(10));
	    }
	    if( dir == 1 ){
	        Collections.sort(output);
	    } else if( dir == -1 ){
	        Collections.sort(output);
	        Collections.reverse(output);
	    }
	    return output;
	} 

	// Define the static method arrayListOfArrayLists here
	public static ArrayList<ArrayList<Integer>> arrayListOfArrayLists(int m, int n, int dir){
	    ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
	    for(int i = 0; i < m; i++){
	        output.add(randomArrayList(n, dir));
	    }
	    return output;
	}

    public static void main( String[] args )
    {
    	final int NTESTS = 100;
    	final int ARRSIZE = 10;
    	final int DIR = 1;
    	String[] orderTypes = { "decreasing", "random", "increasing" };

    	ArrayList<ArrayList<Integer>> a = arrayListOfArrayLists( NTESTS, ARRSIZE, DIR );
    	APTimer timer = new APTimer();
    	Sort s = new InsertionSort();

    	timer.start();
    	for ( ArrayList<Integer> aList : a )
    	  s.sortList( aList );
    	timer.stop();

    	System.out.println( "Average time to sort an ArrayList of size " + ARRSIZE + 
    	                    ", initially in " + orderTypes[ DIR + 1 ] + " order: " +
    	                    (timer.interval() / (double)NTESTS) + " ms." );
    }
    
}
