package com.eimacs.lab03gui;

import com.eimacs.lab03.Deck;
import com.eimacs.lab03.Hand;
import javax.swing.JFrame;

/**
 * A JFrame that displays a Hand of Cards
 * 
 * @author IMACS Curriculum Development Group
 * @version 2.0 January 14, 2015
 */
@SuppressWarnings("serial")
public class Lab03Runner extends JFrame
{
    /**
     * Class constructor
     * 
     * @param title  the title to be displayed in the JFrame's title bar
     */
    public Lab03Runner( String title )
    {
        super( title );
        initComponents();
    }
    
    /**
     * Initializes the JFrame's components
     */
    private void initComponents()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //getContentPane().setLayout( new java.awt.GridLayout( 0, 1 ) );
        
        Deck d = new Deck();
        d.shuffle();
        /*
        Hand[] hands = new Hand[ 4 ];
        for ( int i = 0; i < hands.length; i++ )
          hands[ i ] = new Hand();

        for ( int i = 0; i < hands.length * 5; i++ )
          hands[ i % hands.length].addCard( d.dealCard() );

        for ( int i = 0; i < hands.length; i++ )
          add( new HandPanel( hands[ i ] ) );
         
        Hand h = new Hand();
        for ( int i = 0; i < 5; i++ )
            h.addCard( d.dealCard() );
        
        add( new HandPanel( h ) );
        */
        Hand h = new Hand();
        for ( int i = 0; i < 5; i++ )
          h.addCard( d.dealCard() );
        add( new FannedHandPanel( h ) );
        
        pack();
    }
    
    /**
     * The main method launches the Lab03Runner JFrame
     * 
     * @param args  not used
     */
    public static void main( String[] args )
    {
    	/*
    	* When instructed to do so, uncomment the following three
    	* statements and comment out the rest of the main method
    	* before running the project.
    	*/
    	//UserInfoDialog dlg = new UserInfoDialog( null, true );
    	//dlg.setLocationRelativeTo( null );
    	//dlg.setVisible( true );

    	/* Create and display the form */
    	java.awt.EventQueue.invokeLater( new Runnable()
    	{
    	  public void run()
    	  {
    	    //new Lab03Runner( "Poker" ).setVisible( true );
    	    Lab03Runner theFrame = new Lab03Runner( "Poker" );
    	    theFrame.setSize( 330, 250 );
    	    theFrame.setVisible( true );
    	  }
    	} );
    }
}
