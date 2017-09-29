package mainPackage;

import java.util.*;

public class Updater extends Thread{
	private ArrayList<AttributePanel> panels = new ArrayList<>();
	
	public void run(){
		while(true){
			for(AttributePanel panel : panels){
				panel.updateToAttribute();
				System.out.println("Updated: " + panel.getAttribute());
			}
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void add(AttributePanel panel){
		if(panel != null) getPanels().add(panel);
	}
	public AttributePanel remove(int index){
		return getPanels().remove(index);
	}
	public ArrayList<AttributePanel> getPanels() {
		return panels;
	}
	public void setPanels(ArrayList<AttributePanel> panels) {
		this.panels = panels;
	}
}
