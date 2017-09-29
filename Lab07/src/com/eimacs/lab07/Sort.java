package com.eimacs.lab07;

import java.util.ArrayList;

/**
 *
 * @author IMACS Curriculum Development Group
 * @version 2.0 January 14, 2015
 */
public abstract class Sort
{
    public abstract <T extends Comparable<T>> void sortList( ArrayList<T> arr );    
}
