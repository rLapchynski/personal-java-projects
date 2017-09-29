package com.eimacs.lab06;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 * @version 2.1 (January 14, 2015) IMACS Curriculum Development Group
 */
public class Picture extends SimplePicture
{
      ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     *
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int width, int height)
    {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

      ////////////////////// methods ///////////////////////////////////////
    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName()
                + " height " + getHeight()
                + " width " + getWidth();
        return output;
    }

    /**
     * Method to set the blue component to 0
     */
    public void zeroBlue()
    {
        Pixel[][] pixels = getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }
    
    

    /**
     * Method that mirrors the left half of a picture onto the
     * right half as though reflecting in a mirror placed on 
     * the vertical center line of the picture
     */
    public void mirrorLeftOntoRight()
    {
        Pixel[][] pixels = getPixels2D();
        Pixel leftPixel, rightPixel;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][(width - 1) - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }
    public void repairTemple()
    {
        Pixel[][] pixels = getPixels2D();
        Pixel leftPixel, rightPixel;
        int width = pixels[0].length;
        for (int row = 28; row < 96; row++)
        {
            for (int col = 14; col < 277; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][(width - 1) - col -14];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }
    public void flipLeftAndRight(){
    	Pixel[][] pixels = getPixels2D();
        Pixel leftPixel, rightPixel, storedPixel;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][(width - 1) - col];
                storedPixel = new Pixel(this,0,0);
                storedPixel.setColor(rightPixel.getColor());
                rightPixel.setColor(leftPixel.getColor());
                leftPixel.setColor(storedPixel.getColor());
            }
        }
    }
    public void mirrorTopOntoBottom(){
    	Pixel[][] pixels = getPixels2D();
        Pixel topPixel, bottomPixel;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length / 2; row++)
        {
            for (int col = 0; col < width; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[(pixels.length - 1) - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }
    public void negate(){
    	Pixel[][] pixels = getPixels2D();
    	  for ( Pixel[] rowArray : pixels )
    	  {
    	    for ( Pixel pixelObj : rowArray )
    	    {
    	      pixelObj.setBlue( 255 - pixelObj.getBlue() );
    	      pixelObj.setRed( 255 - pixelObj.getRed());
    	      pixelObj.setGreen(255 - pixelObj.getGreen());
    	    }
    	  }
    }
    public void grayscale(){
    	Pixel[][] pixels = getPixels2D();
  	  for ( Pixel[] rowArray : pixels )
  	  {
  	    for ( Pixel pixelObj : rowArray )
  	    {
  	    	int avg = (int)pixelObj.getAverage();
  	    	pixelObj.setBlue(avg);
  	    	pixelObj.setRed(avg);
  	    	pixelObj.setGreen(avg);
  	    }
  	  }
    }
    public void fixUnderwater(){
    	Pixel[][] pixels = getPixels2D();
  	  	for ( Pixel[] rowArray : pixels )
  	  	{
  	  		for ( Pixel pixelObj : rowArray )
  	  		{
  	  			pixelObj.setRed( pixelObj.getRed() * 3);
  	  		}
  	  	}
    }
    public void cloneSeagull(){
    	Pixel[][] pixels = getPixels2D();
        Pixel leftPixel, rightPixel, storedPixel;
        int width = pixels[0].length;
        for (int row = 230; row < 342; row++)
        {
            for (int col = 230; col < 410; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row+30][col+238];
                storedPixel = new Pixel(this,0,0);
                storedPixel.setColor(rightPixel.getColor());
                rightPixel.setColor(leftPixel.getColor());
                // leftPixel.setColor(storedPixel.getColor());
            }
        }
    }
    public void edgeDetect(int minColorDistance){
    	Pixel[][] pixels = getPixels2D();
        Pixel thisPix, rightPix, belowPix;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length - 1; row++)
        {
            for (int col = 0; col < width - 1; col++)
            {
                thisPix = pixels[row][col];
                rightPix = pixels[row + 1][col];
                belowPix = pixels[row][col+1];
                if(thisPix.colorDistance(rightPix.getColor()) > minColorDistance || thisPix.colorDistance(belowPix.getColor()) > minColorDistance){
                	thisPix.setColor(Color.BLACK);
                } else {
                	thisPix.setColor(Color.WHITE);
                }
                
            }
        }
    }


} // this } is the end of class Picture, put all new methods before this