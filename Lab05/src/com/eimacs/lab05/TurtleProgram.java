package com.eimacs.lab05;

import java.awt.Graphics;
import java.util.ArrayList;
import com.eimacs.lab05gui.Turtle;

/**
 *
 * @author |Your name|
 * @version 1.0 |Today's date|
 */
public class TurtleProgram
{
	private boolean isValid;
	private ArrayList<Action> myActions;
	public TurtleProgram(){
		myActions = new ArrayList<Action>();
		isValid = false;
	}
	public void addAction(Action a){
		myActions.add(a);
		isValid = false;
	}
	public String toString(){
		String retStr = "";
		for(Action a : myActions)
			retStr += a + "\n";
		return retStr.length() == 0 ? retStr : retStr.substring(0, retStr.length()-1);
	} 
	public void execute(Turtle t, Graphics g){
		if(!isValid) return;
		for(Action a : myActions)
			a.execute(t, g);
	}
	public void setIsValid(boolean v){
		isValid = v;
	}
}
