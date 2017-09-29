package mainPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;
import java.math.*;
import javax.swing.*;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

@SuppressWarnings("unused")
public class _GamesMainClass {
	
	public static JFrame mainFrame = new JFrame("Game Creator");
	
	public static int gamesBtnsY = 5;
	public static int currentGameSelected;
	public static List<String> gameIdentifiers = new ArrayList<String>();
	
	public static JLabel previewLabel = new JLabel();
	public static JButton runGameButton = Buttons.runGameButton();
	
	public static void main(String args[]) {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setIconImage(FileUtil.nTilde.getImage());
        //mainFrame.setUndecorated(true);
        
        previewLabel.setIcon(FileUtil.greyBackground);
        previewLabel.setLocation(Frames.scale(new Dimension(210, 5)).width, Frames.scale(new Dimension(210, 5)).height);
        previewLabel.setBackground(Color.DARK_GRAY);
        previewLabel.setSize(Frames.scale(new Dimension(800, 600)));
        mainFrame.add(previewLabel);
        
		String[] gamesList = FileUtil.fileList("bin/GamesList.txt");
		for(int i = 0; i < gamesList.length; i++){
			if(i%5 == 0){
				mainFrame.getContentPane().add(
        		Buttons.gameTypeButton((gamesList[i].substring(gamesList[i].indexOf("[")+1, gamesList[i].indexOf("]"))), 
        				FileUtil.scaleToIcon((gamesList[i+1].substring(gamesList[i+1].indexOf("[")+1, gamesList[i+1].indexOf("]"))), 300, 400),
        				i/5)
        		);
				gameIdentifiers.add(gamesList[i+4]);
			}
		}
		
		runGameButton.setEnabled(false);
		mainFrame.getContentPane().add(runGameButton);
        
        mainFrame.pack();
        Frames.currentlyFullScreen = false;
        Frames.setFull(mainFrame);
        mainFrame.setVisible(true);
    }
}
