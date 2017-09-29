package MiscMain;

import utilities.*;
import java.awt.TrayIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

import MiscMain.CRTrayIcon.Comic;

public class CR_Main {
	
	public static JFrame mainFrame = new JFrame("Comic Viewer");
	
	public static void main(String[] args) throws IOException {
		
		String path = "C:/Users/Ryan Lapchynski/Pictures/Comics/QuestionableContent";
		
		File[] listOfFiles = new File(path).listFiles();
		
		for(int i = Integer.parseInt(listOfFiles[listOfFiles.length-1].getName().substring(0, 6)) + 1; true; i++){
			try{
				WebUtils.fetchComic("http://www.questionablecontent.net/comics/" + i, path + "/" + Data.Utils.zFill(i, 6));
				System.out.print(i);
				System.out.println("  .");
			}catch(FileNotFoundException e){
				System.out.println("Image not Found: " + e.getMessage());
				break;
			}
		}
		/*
		int i = 0;
		for(String line : Files.fileList("C:/Users/Ryan Lapchynski/Pictures/Downloaded/URLs2.txt")){
			i++;
			if(line.equals("")) continue;
			line = line.substring(7);
			try{
				WebUtils.fetchComic(line, "C:/Users/Ryan Lapchynski/Pictures/Comics/xkcd" + "/" + Num.zFill(i, 6) + line.substring(line.length()-4));
				System.out.print(i);
				System.out.println("  .");
			}catch(FileNotFoundException e){
				System.err.println("Image not Found: " + e.getMessage());
				//break;
			}
		}
		
		/*
		File folder = new File(path);
		//File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				Files.writeLine(i+1, listOfFiles[i].getName(), path + "/Index.txt");
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

		/*
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		mainFrame.setSize(750, 750); 
		mainFrame.setLocationRelativeTo(null); 
		
		mainFrame.setVisible(true);
		*/
		
		/*
		CRTrayIcon.addTrayIcon();
		updateComics uc = new updateComics();
		uc.start();
		
		
		for(String line : Files.fileList("C:/Users/Ryan Lapchynski/Pictures/Downloaded/URLs.txt")){
			Files.writeNewLine(line.substring(line.indexOf("http")), "C:/Users/Ryan Lapchynski/Pictures/Downloaded/URLs2.txt");
			System.out.println(line.substring(0, 5));
		}
		*/
		
	}

}

class updateComics extends Thread{
	
	@Override
	public void run() {
		Thread.yield();
		
		while(true) {
			
			if(CRTrayIcon.checkForNewComic(Comic.QuestionableContent)){
				CRTrayIcon.trayIcon.displayMessage("Found New Questionable Content", "Found new comic", TrayIcon.MessageType.INFO);
			}
			
			try {
				Thread.sleep(5 * 60 * 1000);
			} catch (InterruptedException e) {
			}
		}
	}

}

