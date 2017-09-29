package com.eimacs.lab07;

import java.util.ArrayList;

/**
 *
 * @author |your name|
 * @version 1.0 |today's date|
 */
public class InsertionSort extends Sort 
{ 
  public <T extends Comparable<T>> void sortList( ArrayList<T> arr ) 
  { 
      for ( int i = 0 ; i < arr.size() ; i++ )
      {
        T next = arr.get(i);
        int insertindex = 0;
        int k = i;
        while ( k > 0 && insertindex == 0 )
        {
          if ( next.compareTo( arr.get(k-1) ) > 0 )
          {
            insertindex = k;
          }
          else
          {
            arr.remove(k);
            arr.add(k, arr.get(k-1) );
          }
          k--;
        }
        arr.remove(insertindex);
        arr.add(insertindex, next);
      }
      
  } 
} 
