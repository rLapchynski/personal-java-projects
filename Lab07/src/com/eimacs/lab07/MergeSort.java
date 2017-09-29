package com.eimacs.lab07;

import java.util.ArrayList;

/**
 *
 * @author |your name|
 * @version 1.0 |today's date|
 */
public class MergeSort extends Sort 
{ 
  public <T extends Comparable<T>> void sortList( ArrayList<T> arr ) 
  { 
    mergeSort( arr, 0, arr.size() - 1 ); 
  } 

  public <T extends Comparable<T>> void mergeSort( ArrayList<T> a, int low, int high ) 
  { 
    if ( low >= high ) 
      return; 

    int mid = ( low + high ) / 2; 

    mergeSort( a, low, mid ); 
    mergeSort( a, mid + 1, high ); 

    merge( a, low, mid, high ); 
  } 

  public <T extends Comparable<T>> void merge( ArrayList<T> a, int low, int mid, int high ) 
  { 
    
      ArrayList<T> temp = new ArrayList<T>();
    
      int i = low, j = mid + 1, n = 0;
    
      while ( i <= mid || j <= high )
      {
        if ( i > mid )
        {
          temp.add(a.get(j));
          j++;
        }
        else if ( j > high )
        {
          temp.add(a.get(i));
          i++;
        }
        else if ( a.get( i ).compareTo(a.get( j )) < 0 )
        {
          temp.add(a.get(i));
          i++;
        }
        else
        {
          temp.add(a.get(j));
          j++;
        }
        n++;
      }
    
      for ( int k = low ; k <= high ; k++ ){
        a.remove(k);
        a.add( k , temp.get(k - low));
      }
        
  } 
} 