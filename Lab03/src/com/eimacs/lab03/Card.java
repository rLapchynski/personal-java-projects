package com.eimacs.lab03;

/**
 * Models a standard playing card (not a joker)
 * 
 * @author  IMACS Curriculum Development Group
 * @version 2.0 January 14, 2015
 */
public class Card
{
    /** This card's suit */
    private String mySuit;
    /** This card's pip value (aces high) */
    private int myValue;
    /** The English names of the cards in a suit */
    private String[] cardNames =
    {
        "Deuce", "Three", "Four", "Five", "Six", "Seven", 
        "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"
    };

    /**
     * Class constructor
     * 
     * @param suit  a String, either "spades", "hearts", "diamonds", or "clubs"
     * @param value  an int from 2 through 14
     */
    public Card( String suit, int value )
    {
        mySuit = suit;
        myValue = value;
    }

    /**
     * Gets the full English name of this card
     * 
     * @return the full name of this card (in English)
     */
    public String name()
    {
        return cardNames[ myValue - 2] + " of " + mySuit;
    }

    /**
     * Gets this card's suit
     * 
     * @return this card's suit
     */
    public String getSuit()
    {
        return mySuit;
    }

    /**
     * Gets this card's pip value (aces high)
     * 
     * @return this card's pip value (aces high)
     */
    public int getValue()
    {
        return myValue;
    }

    /**
     * Gets the filename of this card's image
     * 
     * @return the filename of this card's image
     */
    public String imageFileName()
    {
        if ( myValue < 11 )
        {
            return myValue + "_of_" + mySuit + ".png";
        }
        else
        {
            return cardNames[ myValue - 2 ].toLowerCase() +
                    "_of_" + mySuit + ".png";
        }
    }
}
