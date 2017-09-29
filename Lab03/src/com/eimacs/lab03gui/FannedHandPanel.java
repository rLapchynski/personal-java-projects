package com.eimacs.lab03gui;

import com.eimacs.lab03.Hand;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A JPanel that displays a Hand of Cards in a fan
 *
 * @author  |your name|
 * @version  1.0 |today's date|
 */
@SuppressWarnings("serial")
public class FannedHandPanel extends JPanel
{
  /**
   * The buffered images of the Cards in the Hand
   */
  private BufferedImage[] bImgs = new BufferedImage[ 5 ];

  /**
   * Class constructor
   *
   * @param h  the hand to be displayed
   */
  public FannedHandPanel( Hand h )
  {
    for ( int i = 0; i < 5; i++ )
    {
      String path = "cards/" + h.getHand().get( i ).imageFileName();
      URL imgURL = FannedHandPanel.class.getResource( path );
      try
      {
        bImgs[ i ] = ImageIO.read( imgURL );
      }
      catch ( IOException e )
      {
      }
    }
  }

  /**
   * Paints the buffered images of the Cards in the Hand
   *
   * @param g  the Graphics object onto which to paint
   */
  public void paintComponent( Graphics g )
  {
    super.paintComponent( g );
    BufferedImage bi = bImgs[ 0 ];
    Graphics2D g2 = (Graphics2D)g;
    g2.translate( 110, 20 );
    g2.rotate( -Math.PI/2, bi.getWidth()/2, bi.getHeight() );
    for ( int i = 0; i < 5; i++ )
    {
      bi = bImgs[ i ];
      g2.rotate( Math.PI/6, bi.getWidth()/2, bi.getHeight() );
      g2.drawImage( bi, 0, 0, null );
    }
  }
}