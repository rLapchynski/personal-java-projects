package com.eimacs.lab04;

public class BinItem 
{ 
  private String mySKU;
  private int myQuantity;
  
  public BinItem(String SKU, int quantity){
      mySKU = SKU;
      myQuantity = quantity;
  }
  
  public int getQuantity(){
      return myQuantity;
  }
  
  public String getSKU(){
      return mySKU;
  }
  
  public String toString(){
      return "SKU " + mySKU + ": " + myQuantity;
  }  
} 