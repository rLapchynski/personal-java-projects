package MiscMain;

import java.awt.Graphics;
import java.awt.MouseInfo;


import javax.swing.JPanel;



public class drawingpane extends JPanel {

	public void paintMe( Graphics g )
	{
		g.drawLine((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(), (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());
	}

	public void paintComponent( Graphics g )
	{
		//super.paintComponent( g );
		paintMe( g );
		System.out.println("blah");
	}
	
	

}
