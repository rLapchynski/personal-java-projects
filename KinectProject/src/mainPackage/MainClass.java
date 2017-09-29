package mainPackage;

import edu.ufl.digitalworlds.j4k.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainClass {
	
	public static Kinect kinect;
	
	public static JFrame mainFrame;
	public static KinectPanel kinectPanel;

	public static void main(String[] args) {
		
		kinect = new Kinect();
		kinect.start(J4KSDK.DEPTH);
		kinect.setDepthResolution(640, 480);
		
		mainFrame = new JFrame("Kinect");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kinectPanel = new KinectPanel();
		
		
		
		kinectPanel.setSize(500, 500);
		kinectPanel.setLocation(0, 0);
		mainFrame.add(kinectPanel);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		while(true){
			short[] frame = kinect.getDepthFrame();
			
			if(frame != null)
			for(int i = 0; i < frame.length; i++){
				float f = frame[i] / Short.MAX_VALUE;
				kinectPanel.drawPoint(i % 480, i / 480, new Color(f, f, 0.5f));
			}
		}

	}

}
