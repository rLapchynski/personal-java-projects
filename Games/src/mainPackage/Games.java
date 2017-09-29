package mainPackage;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;
import java.math.*;
import javax.swing.*;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import spanishGames.*;

@SuppressWarnings("unused")
public class Games {
	public static Jeapardy jeapardy = new Jeapardy();
	
	public static void runGame(String gameIdentifier){
		switch(gameIdentifier){
			case "Jeapardy":
				jeapardy.run();
				break;
			case "Jeapardy00":
				new CreateGameFiles();
				break;
		}
	}
}

class Jeapardy{
	public JFrame jepMain = new JFrame("Jeapardy");
	JLabel team1Name = Buttons.teamNameLabel(jepMain, "Team 1 Name", 5, 0);
	JLabel team2Name = Buttons.teamNameLabel(jepMain, "Team 2 Name", 200, 0);	
	
	public void run(){
		jepMain.setLayout(null);
		
		jepMain.setUndecorated(true);
		Frames.currentlyFullScreen = true;
        Frames.setFull(jepMain);
        
        JButton closeBtn = Buttons.closeButton(jepMain, 5, 5);
        closeBtn.setLocation(Frames.screenHorizontalResolution - closeBtn.getWidth() - 5, 5);
        jepMain.add(closeBtn);
        
        jepMain.setBackground(Color.getColor("", Color.TRANSLUCENT));
        
        team1Name.setFont(new Font(team1Name.getFont().getName(), Font.PLAIN, Frames.titleFontSize));
		team1Name.setSize(team1Name.getPreferredSize());
		jepMain.add(team1Name);
		
		team2Name.setFont(new Font(team2Name.getFont().getName(), Font.PLAIN, Frames.titleFontSize));
		team2Name.setSize(team2Name.getPreferredSize());
		team2Name.setLocation(500, 0);
		jepMain.add(team2Name);
		
		jepMain.pack();
        jepMain.setVisible(true);		
	}
}
