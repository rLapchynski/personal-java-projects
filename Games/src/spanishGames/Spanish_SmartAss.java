package spanishGames;

import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit.*;
import java.util.*;
import java.util.stream.Stream;
import java.math.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import mainPackage.*;

@SuppressWarnings("unused")
public class Spanish_SmartAss {
	public static ImageIcon background = FileUtil.scaleToIcon(
			(System.getProperty("user.dir") + "/bin/images/RGB_Wheel.png"), 456, 456);
	public static JFrame mainFrame = new JFrame();
	public static JLabel ImageLabel = new JLabel();
	
	public static void main(String[] args) {
		mainFrame.setLayout(null);
		mainFrame.setBackground(Color.DARK_GRAY);
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setPreferredSize(new Dimension(456, 456));
		mainFrame.setLocation(50, 50);
		
		ImageLabel.setIcon(background);
		ImageLabel.setSize(ImageLabel.getPreferredSize());
		ImageLabel.setLocation(0,0);
		ImageLabel.setBackground(Color.DARK_GRAY);
		mainFrame.add(ImageLabel);
		
		JLabel plyr1 = new JLabel();
		plyr1.setIcon(FileUtil.scaleToIcon(
				(System.getProperty("user.dir") + "/bin/images/nTilde.png"), 30, 20));
		plyr1.setLocation(10, 10);
		plyr1.setSize(plyr1.getPreferredSize());
		mainFrame.add(plyr1);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}