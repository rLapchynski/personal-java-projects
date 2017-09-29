package com.eimacs.lab04;

import java.util.ArrayList;

public class Bin 
{ 
  private String myName;
  private ArrayList<BinItem> myContents;
  
  public Bin(String name){
      myName = name;
      myContents = new ArrayList<BinItem>();
  }
  
  public String getName(){
      return myName;
  }
  
  public ArrayList<BinItem> getContents(){
      return myContents;
  }
  
  public void remove(int i){
      myContents.remove(i);
  }
  
  public int totalQuantity(){
      int retInt = 0;
      for(BinItem item : myContents){
          retInt += item.getQuantity();
      }
      return retInt;
  }
  
  public void add(BinItem b){
      for(int i = 0; i < myContents.size(); i++){
          if(b.getSKU().equals(myContents.get(i).getSKU())){
              myContents.set(i, new BinItem(b.getSKU(), myContents.get(i).getQuantity()+b.getQuantity()));
              return;
          }
      }
      myContents.add(b);
  }
  
  public String toString(){
      String retStr = "Bin " + myName + ":\n";
      for(BinItem item : myContents){
          retStr += "SKU " + item.getSKU() + ": " + item.getQuantity() + "\n";
      }
      return retStr.substring(0, retStr.length()-1);
  } 
} 