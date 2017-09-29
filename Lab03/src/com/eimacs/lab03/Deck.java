package com.eimacs.lab03;

import java.util.ArrayList;

/**
 * Models a deck of standard playing cards (without jokers)
 * 
 * @author  IMACS Curriculum Development Group
 * @version 2.0 January 14, 2105
 */
public class Deck
{
    /** This deck of Cards */
    private ArrayList<Card> myDeck;
    /** The names of the card suits */
    private String[] suits = { "spades", "hearts", "diamonds", "clubs" };

    /**
     * Class constructor that initializes myDeck to a standard deck of 52 Cards (without jokers)
     */
    public Deck()
    {
        myDeck = new ArrayList<Card>( 52 );
        for ( String s : suits )
        {
            for ( int v = 2; v < 15; v++ )
            {
                myDeck.add( new Card( s, v ) );
            }
        }
    }

    /**
     * Gets this Deck
     * 
     * @return this Deck as an ArrayList of Cards
     */
    public ArrayList<Card> getDeck()
    {
        return myDeck;
    }

    /**
     * Prints a listing of the English names of the Cards in this Deck
     */
    public void printDeck()
    {
        for ( Card c : myDeck )
        {
            System.out.println( c.name() );
        }
    }
    
    /**
     * Shuffles this Deck
     */
    public void shuffle()
    {
        for ( int i = 0; i < 500; i++ )
        {
            int rn = (int)(52 * Math.random());
            myDeck.add( myDeck.remove( rn ) );
        }
    }
    
    /**
     * If possible, deals a Card from this Deck
     * 
     * @return the first Card in myDeck (if it's not empty) or null (otherwise)
     */
    public Card dealCard()
    {
        if ( myDeck.size() > 0 )
        {
            return myDeck.remove( 0 );
        }
        else
        {
            System.out.println( "The deck is empty." );
            return null;
        }
    }
}
