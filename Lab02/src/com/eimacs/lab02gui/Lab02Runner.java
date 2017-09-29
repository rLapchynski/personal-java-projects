package com.eimacs.lab02gui;

import com.eimacs.lab02.APPoint;
import com.eimacs.lab02.APRectangle;
import javax.swing.JFrame;

/**
 *
 * @author |your name|
 * @version 1.0 |today's date|
 */
public class Lab02Runner
{
    /**
     * The main method
     * 
     * @param args the command line arguments
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

	  JFrame frame = new JFrame( "AP Lab 02" ); 
	  frame.getContentPane().add( new APCanvas() ); 
	  frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
	  frame.pack(); 
	  frame.setSize( 480, 504 ); 
	  frame.setLocation( 100, 100 ); 
	  frame.setVisible( true ); 
	}
    
    public static String printAPPoint( APPoint p ){
    return "(" + p.getX() + "," + p.getY() + ")";
    }
    
    public static String printAPRectangle ( APRectangle r ){
        return "[APRectangle " + printAPPoint( r.getTopLeft() ) + " " + r.getWidth() + "," + r.getHeight() + "]";
    }
}
