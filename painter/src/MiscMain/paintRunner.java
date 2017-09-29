package MiscMain;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class paintRunner {

	
	public static JFrame mainFrame = new JFrame("Painter");
	public static drawingpane mainPane = new drawingpane();
	
	public static void FrameSetup(){
			// setting layout to gridbaglayout
			mainFrame.setLayout(new FlowLayout());
			// setting close function
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//sets mainframe size
			mainFrame.setSize(500,500);	
	}
	
	
	public static void componentSetup(){
		mainPane.addMouseListener(new MouseInputListener() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub		
				mainPane.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mainPane.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mainPane.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mainPane.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		componentSetup();
		mainFrame.add(mainPane);
		mainFrame.setContentPane(mainPane);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setSize(700,500);
		mainFrame.setLocationRelativeTo(null);
	}

}
