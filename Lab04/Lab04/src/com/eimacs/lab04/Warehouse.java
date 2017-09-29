package com.eimacs.lab04;

import java.util.ArrayList;

public class Warehouse 
{ 
  private int myBinMax;
  private ArrayList<Footwear> myCatalog;
  private ArrayList<Bin> myBins;
  
  public Warehouse(int binMax){
      myBinMax = binMax;
      myCatalog = new ArrayList<Footwear>();
      myBins = new ArrayList<Bin>();
      
      for(int i = 0; i < 5; i++) 
          addBin();
  }
  
  public void addBin(){
      myBins.add(new Bin("B" + myBins.size()));
  }
  
  public String ship(String sku, int amount){
	  String retStr = "";
	  ArrayList<Bin> contBins = find(sku);
	  
	  loop0:
	  for(Bin bin : contBins){
		  for(int i = 0; i < bin.getContents().size(); i++){
			  if(bin.getContents().get(i).getSKU().equals(sku)){
				  if(bin.getContents().get(i).getQuantity() < amount){
					  amount -= bin.getContents().get(i).getQuantity();
					  retStr += bin.getName() + ": " + sku + ", " + bin.getContents().get(i).getQuantity() + "\n";
					  bin.remove(bin.getContents().indexOf(bin.getContents().get(i)));
				  } else {
					  BinItem tmp = new BinItem(sku, bin.getContents().get(i).getQuantity() - amount);
					  bin.remove(bin.getContents().indexOf(bin.getContents().get(i)));
					  bin.add(tmp);
					  retStr += bin.getName() + ": " + sku + ", " + amount + "\n";
					  amount = 0;
					  break loop0;
				  }
			  } else{
				  continue;
			  }
		  }
	  }
	  
	  if(amount > 0){
		  retStr += "Back order: " + sku + ", " + amount + "\n";
	  }
	  
	  return retStr.substring(0, retStr.length()-1);
  }
  
  public String toString(){
      String retStr = "";
      for(Bin bin : myBins){
          retStr += "Bin " + bin.getName() + ":\n";
          for(BinItem item : bin.getContents()){
              retStr += Lab04Runner.lookupFootwear(myCatalog, item.getSKU()) + ", " + item + "\n";
          }
      }
      
      return retStr.substring(0, retStr.length()-1);
  }  
  
  public ArrayList<Bin> find( String sku )
  {
  ArrayList<Bin> retArr = new ArrayList<Bin>();
  
  for(Bin bin : myBins){
      for(BinItem item : bin.getContents()){
          if(item.getSKU().equals(sku)){
              retArr.add(bin);
              break;
          }
      }
  }
  
  return retArr;
  }
  
  public void receive(Footwear fw, int amount){
	  if(!myCatalog.contains(fw)) myCatalog.add(fw);
	  
	  while(amount > 0){
		  int currLeast = 0;
		  
		  for(int i = 1; i < myBins.size(); i++){
			  if(myBins.get(i).totalQuantity() < myBins.get(currLeast).totalQuantity()){
				  currLeast = i;
			  }
		  }
		  //System.out.print("Amount: " + amount + "	currLeast: " + currLeast + "	totalQuantity: " +  myBins.get(currLeast).totalQuantity() + "	Type: " + fw.toString().substring(0, 1));
		  
		  
		  if(myBins.get(currLeast).totalQuantity() == myBinMax){
			  addBin();
		  }else if(amount > myBinMax - myBins.get(currLeast).totalQuantity()){
			  int tmp = myBinMax - myBins.get(currLeast).totalQuantity();
			  myBins.get(currLeast).add(new BinItem(fw.getSKU(), tmp));
			  amount -= tmp;
			  //System.out.println(" if");
		  } else{
			  //System.out.println(" else");
			  myBins.get(currLeast).add(new BinItem(fw.getSKU(), amount));
			  amount = 0;
		  }
	  }
  }
}