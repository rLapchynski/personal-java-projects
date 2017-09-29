package com.eimacs.lab02gui;

import java.awt.Graphics; 
import javax.swing.JPanel;

import com.eimacs.lab02.APPoint;
import com.eimacs.lab02.APRectangle; 

/** 
 * A panel on which to draw 
 * 
 * @author |your name| 
 * @version 1.0 |today's date| 
 */ 
@SuppressWarnings("serial")
public class APCanvas extends JPanel 
{ 
  /** 
   * The class constructor 
   */ 
  public APCanvas() 
  { 
  } 

  /** 
   * Draws on a Graphics object 
   * 
   * @param g  a Graphics object 
   */ 
  private void paintMe( Graphics g ){
	  APPoint p = new APPoint( 60, 100 ); 
	  APRectangle r = new APRectangle( p, 360, 280 ); 
	  for(int i = 0; i < 36; i++){
		  r.draw(g);
		  r.shrink(95);
	  }
  } 

  /** 
   * Overrides JPanel's paintComponent method 
   * 
   * @param g  a Graphics object 
   */ 
  public void paintComponent( Graphics g ) 
  { 
    super.paintComponent( g ); 
    paintMe( g ); 
  } 
}