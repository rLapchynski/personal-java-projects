package com.eimacs.lab02;

import java.awt.Graphics;

/**
 *
 * @author |your name|
 * @version 1.0 |today's date|
 */
public class APRectangle 
{ 
  private APPoint myTopLeft; 
  private double  myWidth; 
  private double  myHeight; 

  public APRectangle( APPoint topLeft, double width, double height ) 
  { 
    // Insert code to initialize the instance variables here
     myTopLeft = topLeft;
     myWidth = width;
     myHeight = height;  } 
   // Insert code for the accessor methods getTopLeft, getWidth, getHeight
  // and the modifier methods setTopLeft, setWidth, setHeight here
   public APPoint getTopLeft(){
       return myTopLeft;
   }
   public double getWidth(){
       return myWidth;
   }
   public double getHeight(){
       return myHeight;
   }
   
   public void setTopLeft(APPoint topLeft){
       myTopLeft = topLeft;
   }
   public void setWidth(double width){
       myWidth = width;
   }
   public void setHeight(double height){
       myHeight = height;
   }
   
   public APPoint getTopRight()
   {
       return new APPoint( myTopLeft.getX() + myWidth, myTopLeft.getY() );
   }

   public APPoint getBottomLeft()
   {
       return new APPoint( myTopLeft.getX(), myTopLeft.getY() + myHeight );
   }

   public APPoint getBottomRight()
   {
       return new APPoint( myTopLeft.getX() + myWidth, myTopLeft.getY() + myHeight );
   }

   public void shrink( double d )
   {
       myWidth *= d/100.0;
       myHeight *= d/100.0;
   }
   
   public void draw( Graphics g ) 
   { 
     APPoint topLeft = myTopLeft; 
     APPoint topRight = getTopRight(); 
     APPoint bottomLeft = getBottomLeft(); 
     APPoint bottomRight = getBottomRight(); 

     g.drawLine( (int)topLeft.getX(), (int)topLeft.getY(), (int)topRight.getX(), (int)topRight.getY() ); 
     g.drawLine( (int)topLeft.getX(), (int)topLeft.getY(), (int)bottomLeft.getX(), (int)bottomLeft.getY() ); 
     g.drawLine( (int)bottomLeft.getX(), (int)bottomLeft.getY(), (int)bottomRight.getX(), (int)bottomRight.getY() ); 
     g.drawLine( (int)bottomRight.getX(), (int)bottomRight.getY(), (int)topRight.getX(), (int)topRight.getY() ); 
   } 
} 