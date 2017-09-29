package MiscMain;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import utilities.Files;

public class ClipboardRecorder {
	public static final TrayIcon trayIcon = new TrayIcon((new ImageIcon("C:/Users/Ryan Lapchynski/EclipseWorkspace/Resources/icon.png")).getImage());
	public static final String logLocation = "C:/Users/Ryan Lapchynski/Documents/ClipboardLog.txt";
	
	public static void main(String[] args) {
		ClipboardChecker c = new ClipboardChecker();
		c.addEvent(new event(){
			void run(String s){
				//trayIcon.displayMessage("Clipboard Event", "Copied: " + s,  TrayIcon.MessageType.INFO);
			}
		});
		addTrayIcon();
		c.start();
		
	}
	
	public static void addTrayIcon() {
		//Check the SystemTray support
        if (!SystemTray.isSupported()) {
        	try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e){
				e.printStackTrace();
			}
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final SystemTray tray = SystemTray.getSystemTray();
        trayIcon.setImageAutoSize(true);
        
        // Create a popup menu components
        MenuItem showLogItem = new MenuItem("Show Log");
        MenuItem exitItem = new MenuItem("Exit");
        
        //Add components to popup menu
        popup.add(showLogItem);
        popup.addSeparator();
        popup.add(exitItem);
        
        trayIcon.setPopupMenu(popup);
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
        
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from System Tray");
            }
        });
        
        showLogItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Runtime.getRuntime().exec("notepad.exe " + logLocation);
				} catch (IOException e1) {
				}
            }
        });
        
       
        @SuppressWarnings("unused")
		ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem)e.getSource();
                //TrayIcon.MessageType type = null;
                System.out.println(item.getLabel());
                if ("Error".equals(item.getLabel())) {
                    //type = TrayIcon.MessageType.ERROR;
                    trayIcon.displayMessage("Sun TrayIcon Demo",
                            "This is an error message", TrayIcon.MessageType.ERROR);
                    
                } else if ("Warning".equals(item.getLabel())) {
                    //type = TrayIcon.MessageType.WARNING;
                    trayIcon.displayMessage("Sun TrayIcon Demo",
                            "This is a warning message", TrayIcon.MessageType.WARNING);
                    
                } else if ("Info".equals(item.getLabel())) {
                    //type = TrayIcon.MessageType.INFO;
                    trayIcon.displayMessage("Sun TrayIcon Demo",
                            "This is an info message", TrayIcon.MessageType.INFO);
                    
                } else if ("None".equals(item.getLabel())) {
                    //type = TrayIcon.MessageType.NONE;
                    trayIcon.displayMessage("Sun TrayIcon Demo",
                            "This is an ordinary message", TrayIcon.MessageType.NONE);
                }
            }
        };
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }

}

class ClipboardChecker extends Thread{
	public ArrayList<event> events = new ArrayList<>();
	
	public void run(){
		String text = "", prevText = "";
		
		while(true){
			try {
				text = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
			} catch (HeadlessException | UnsupportedFlavorException | IOException e) {
				break;
			} 
			if(!text.equals(prevText)){
				prevText = text;
				
				try{
					Files.writeNewLine(ClipboardRecorder.logLocation, text + "\n\n");
					for(event e : events)
						e.run(text);
				} catch(Exception e){
					throw e;					
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	void addEvent(event e){
		events.add(e);
	}
	
}

abstract class event{
	abstract void run(String s);
}