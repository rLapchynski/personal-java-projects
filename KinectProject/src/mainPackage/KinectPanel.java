package mainPackage;

import java.awt.*;
import javax.swing.*;

public class KinectPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public int x, y;
	public Color c;
	
	public void drawPoint(int x, int y, Color c){
		this.x = x;
		this.y = y;
		this.c = c;
		
		repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(c);
		g.drawLine(x, y, x, y);
	}
}
