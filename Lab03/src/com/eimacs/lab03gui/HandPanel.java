package com.eimacs.lab03gui;

import com.eimacs.lab03.Hand;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A JPanel that displays a Hand of Cards
 *
 * @author |your name|
 * @version 1.0 |today's date|
 */
@SuppressWarnings("serial")
public class HandPanel extends JPanel
{
    /**
     * Class constructor
     *
     * @param h the hand to be displayed
     */
    public HandPanel( Hand h )
    {
        for ( int i = 0; i < 5; i++ )
        {
            String path = "cards/" + h.getHand().get( i ).imageFileName();
            URL imgURL = HandPanel.class.getResource( path );
            if ( imgURL != null )
            {
                ImageIcon imgIcon = new ImageIcon( imgURL );
                add( new JLabel( imgIcon ) );
            }
            else
            {
                System.out.println( "Couldn't find file: " + path );
            }
        }
    }
}
