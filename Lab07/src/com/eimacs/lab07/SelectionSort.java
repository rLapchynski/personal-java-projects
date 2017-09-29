package com.eimacs.lab07;

import java.util.ArrayList;

/**
 *
 * @author |your name|
 * @version 1.0 |today's date|
 */

public class SelectionSort extends Sort 
{ 
  public <T extends Comparable<T>> void sortList( ArrayList<T> arr ) 
  { 
    
      int i, k, posmax;
    
      for ( i = arr.size() - 1 ; i > 0 ; i-- )
      {
        posmax = 0;
    
        for ( k = 1 ; k <= i ; k++ )
        {
          if ( arr.get(k).compareTo(arr.get( posmax )) > 0 )
            posmax = k;
        }
    
        T temp = arr.get(i);
        arr.add(i, arr.get(posmax));
        arr.add(posmax, temp);
        arr.remove(i + 1);
        arr.remove(posmax + 1);
      }  
  } 
} 
