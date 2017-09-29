package com.eimacs.lab03;

import java.util.ArrayList;

/**
 * Models a hand of playing cards
 * 
 * @author  IMACS Curriculum Development Group
 * @version 2.0 January 14, 2015
 */
public class Hand
{
    /** This Hand of Cards */
    private ArrayList<Card> myHand;
    
    /**
     * Class constructor that initializes myHand to an empty ArrayList of Cards
     */
    public Hand()
    {
        myHand = new ArrayList<Card>( 5 );
    }
    
    /**
     * Gets this Hand of Cards
     * 
     * @return this Hand of Cards as an ArrayList
     */
    public ArrayList<Card> getHand()
    {
        return myHand;
    }
    
    /**
     * Prints a listing of the English names of the Cards in this Hand
     */
    public void printHand()
    {
        for ( Card c : myHand )
            System.out.println( c.name() );
    }
    
    public int getCardValue(int n){
        return myHand.get(n).getValue();
    }
    
    public void addCard(Card c){
        for(int i = 0; i < myHand.size() && i < 5; i++){
            if(myHand.get(i).getValue() < c.getValue()){
                myHand.add(i, c);
                return;
            }
        }
        myHand.add(c);
    } 
}
