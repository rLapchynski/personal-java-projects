package com.eimacs.lab04;

/**
 *
 * @author |Your name|
 * @version 1.0 |Today's date|
 */
public class Footwear{
    private String myStyle, 
                 mySKU;
    private double mySize;
    
    public Footwear(String style, double size, String SKU){
        myStyle = style;
        mySize = size;
        mySKU = SKU;
    }
    
    public String getStyle(){
        return myStyle;
    }
    
    public double getSize(){
        return mySize;
    }
    
    public String getSKU(){
        return mySKU;
    }
    
    public String getType(){
        return "Unspecified";
    }
    
    public String printSize(){
        return (mySize % 1 == 0) ? ""+(int)mySize : (int)mySize + "\u00bd";
    }
    
    public String toString(){
        return (getType().equals("Unspecified") ? "" : getType() + " - ") + (myStyle + " (size " + printSize() + ")");
    }
}