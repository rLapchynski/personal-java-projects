package com.eimacs.lab04;

/**
 *
 * @author |Your name|
 * @version 1.0 |Today's date|
 */
public class DressShoe extends Shoe{
    public DressShoe(String style, double size, String SKU){
        super(style, size, SKU);
    }
    public String getType(){
        return "Dress Shoe";
    }
}
