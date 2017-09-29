package mainPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainClass{

	public static JFrame mainFrame = new JFrame();
	public static JLabel middleSchool = new JLabel("-----");
	public static JLabel highSchool = new JLabel("-----");
	public static JLabel closeBtn = new JLabel();
	
	public static int posX, posY;
	
	public static final long[] middlePeriodTimes = {	
			000000,  81500,  91000,  91500, 101000, 101500, 
			111000, 111500, 114000, 114500, 121000, 121500, 
			131000, 131500, 141000, 141500, 151500, 240000};
	public static final long[] highPeriodTimes = {	
			000000,  81500,  91000,  91500, 101000, 101500, 
			111000, 111500, 121300, 121500, 124000, 124500, 
			131000, 131500, 141000, 141500, 151500, 240000};
	public static final String[] middlePeriodNames = {	
			"Before School", "1st Period", "Transition to 2nd", "2nd Period", "Transition to 3rd", "3rd Period", 
			"Midday 1", "Midday 2", "Transition to Lunch", "Lunch", "Transition to 4th", "4th Period", 
			"Transition to 5th", "5th Period", "Transition to 6th", "6th Period", "After School"};
	public static final String[] highPeriodNames = {	
			"Before School", "1st Period", "Transition to 2nd", "2nd Period", "Transition to 3rd", "3rd Period", 
			"Transition to 4th", "4th Period", "Midday 1", "Midday 2", "Transition to Lunch", "Lunch", 
			"Transition to 5th", "5th Period", "Transition to 6th", "6th Period", "After School"};
	
	public static ArrayList<String> settings = new ArrayList<String>();
	
	public static void main(String args[]) {	
		
		try {
			settings = fileList(System.getProperty("user.dir") + "/settings.txt");
			if(settings.size() < 4) { 
				settings.clear();
				throw new FileNotFoundException();
			}
		} catch (FileNotFoundException e) {
			settings.add("true");	//Always on top
			settings.add("0.75");	//Opacity
			settings.add("25");		//Font size
			settings.add("7");		//Corner rounding
		}
		
		loopThread lt = new loopThread();
		lt.start();
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setUndecorated(true);
        mainFrame.setIconImage(new ImageIcon(MainClass.class.getResource("Clock_Icon.png")).getImage());
        mainFrame.setAlwaysOnTop(Boolean.parseBoolean(settings.get(0)));
        moveGUI(mainFrame);
        mainFrame.setOpacity((float)Double.parseDouble(settings.get(1)));
        
        middleSchool.setFont(new Font(middleSchool.getFont().getName(), Font.PLAIN, Integer.parseInt(settings.get(2))));
        middleSchool.setLocation(5,0);
        middleSchool.setSize(middleSchool.getPreferredSize());
        mainFrame.add(middleSchool);
        
        highSchool.setFont(new Font(highSchool.getFont().getName(), Font.PLAIN, Integer.parseInt(settings.get(2))));
        highSchool.setLocation(5, middleSchool.getHeight());
        highSchool.setSize(highSchool.getPreferredSize());
        mainFrame.add(highSchool);
        
        closeBtn.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				closeBtn.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				closeBtn.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
        });
        closeBtn.setBorder(null);
        closeBtn.setText("<html>&#10006</html>");
        closeBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        closeBtn.setSize(closeBtn.getPreferredSize());
        mainFrame.add(closeBtn);
        
        resize();
        mainFrame.setTitle(MainClass.middleSchool.getText());
        mainFrame.setVisible(true);
    }
	
	public static void resize() {
		mainFrame.setSize(Math.max(highSchool.getWidth(), middleSchool.getWidth()) + closeBtn.getWidth() + 10, 
        		highSchool.getHeight() + middleSchool.getHeight());
		mainFrame.setShape(new RoundRectangle2D.Double(0, 0, mainFrame.getWidth(), mainFrame.getHeight(), Integer.parseInt(settings.get(3)), Integer.parseInt(settings.get(3))));
		closeBtn.setLocation(mainFrame.getWidth()-closeBtn.getWidth() - 3 , mainFrame.getHeight()/2 - closeBtn.getHeight()/2);
		
	}
	
	private static void moveGUI(Component component){
	    component.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            posX = e.getX();
	            posY = e.getY();
	        }
	    });
	    component.addMouseMotionListener(new MouseAdapter() {
	        public void mouseDragged(MouseEvent evt) {
	            //sets frame position when mouse dragged            
	            Rectangle rectangle = component.getBounds();
	            component.setBounds(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY, rectangle.width, rectangle.height);
	        }
	    });
	}
	
	private static ArrayList<String> fileList(String fileName/*Actually the file path*/) throws FileNotFoundException{
		/*
		 * Usage:
		 * 		String[] str = fileList("path");
		 * 		*do stuff with str*
		 */
		ArrayList<String> fileLines = new ArrayList<String>();
		String line = null;
		try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
            	fileLines.add(line);
            }   
            
            bufferedReader.close();         
        }
        catch(Exception e){
        	throw new FileNotFoundException();
        }
		
		return fileLines;
	}
}

class loopThread extends Thread {
	
	public static String[] tmpTime;
	public static long[] currTimeList = new long[3];
	public static SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
	public static int dayOfWeek;
	public static String hsText, msText;
	public static Long currentTime;
	public static boolean prevTitle = true;
	
	
	
	@Override
	public void run() {
		while(true) {
			
			dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			tmpTime = sf.format(new Date(System.currentTimeMillis())).split(":");
			currTimeList[0] = Long.parseLong(tmpTime[0]); 
			currTimeList[1] = Long.parseLong(tmpTime[1]);
			currTimeList[2] = Long.parseLong(tmpTime[2]);
			currentTime = Long.parseLong(tmpTime[0] + tmpTime[1] + tmpTime[2]);
			
			hsText = MainClass.highSchool.getText();
			msText = MainClass.middleSchool.getText();
			
			//Changing the title of the window every other loop
			if(MainClass.mainFrame.getTitle().equals(msText) && !prevTitle && !hsText.equals("")) {
				MainClass.mainFrame.setTitle(hsText);
			}else if(MainClass.mainFrame.getTitle().equals(hsText) && prevTitle && !msText.equals("")){
				MainClass.mainFrame.setTitle(msText);
			} else {
				prevTitle = !prevTitle;
			}
			
			if(dayOfWeek != 1 && dayOfWeek != 7) { 										//Weekdays
				for(int i = 0; i < MainClass.highPeriodTimes.length-1; i++) {
					if(isBetween(MainClass.highPeriodTimes[i], MainClass.highPeriodTimes[i+1])){
						if(!hsText.equals(MainClass.highPeriodNames[i])) {
							hsText = ("HS: " + MainClass.highPeriodNames[i]);
						}
							
					}
				}			
				
				for(int i = 0; i < MainClass.middlePeriodTimes.length-1; i++) {
					if(isBetween(MainClass.middlePeriodTimes[i], MainClass.middlePeriodTimes[i+1])){
						if(!msText.equals(MainClass.middlePeriodNames[i])) {
							msText = ("MS: " + MainClass.middlePeriodNames[i]);
						}
							
					}
				}		
			} else { 																	//Weekends
				if(!hsText.equals("HS: Out of Session")) {
					hsText = ("HS: Out of Session");
				}
				if(!msText.equals("MS: Out of Session")) {
					msText = ("MS: Out of Session");
				}
			}
			
			
			//Sun:1, Mon:2, ... Sat:7
			if(hsText.contains("Midday")) {
				if(dayOfWeek == 2 || dayOfWeek == 4 || dayOfWeek == 6) { 				//M,W,F
					String[] tmpLst = {"HS: Transition to Advisory", "HS: Advisory"};
					hsText = (tmpLst[Integer.parseInt(hsText.substring(11))-1]);
				}else if(dayOfWeek == 3 || dayOfWeek == 5) { 							//T,T
					String[] tmpLst = {"HS: Transition to StudyHall", "HS: StudyHall"};
					hsText = (tmpLst[Integer.parseInt(hsText.substring(11))-1]);
				}
			}
			
			if(msText.contains("Midday")) {
				if(dayOfWeek == 2 || dayOfWeek == 4 || dayOfWeek == 6) { 				//M,W,F
					String[] tmpLst = {"MS: Transition to [MWF Mid 1]", "MS: [MWF Mid 2]"};
					msText = (tmpLst[Integer.parseInt(msText.substring(11))-1]);
				}else if(dayOfWeek == 3 || dayOfWeek == 5) { 							//T,T
					String[] tmpLst = {"MS: Transition to [TT Mid 1]", "MS: [TT Mid 2]"};
					msText = (tmpLst[Integer.parseInt(msText.substring(11))-1]);
				}
			}
			
			//Combining them into one label if they are the same.
			if(msText.substring(4).equals(hsText.substring(4))) {
				hsText = ("");
				msText = (msText.substring(4));
				MainClass.resize();
			}else if(msText.equals(hsText.substring(4))){ //If ^^^ has already run once, to keep it from doing weird loop stuff
				hsText = ("");
				MainClass.middleSchool.setSize(MainClass.middleSchool.getPreferredSize());
				MainClass.resize();
			}else {
				MainClass.resize();
			}
			
			MainClass.middleSchool.setText(msText);
			MainClass.highSchool.setText(hsText);
			MainClass.highSchool.setSize(MainClass.highSchool.getPreferredSize());
			MainClass.middleSchool.setSize(MainClass.middleSchool.getPreferredSize());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//Meh.
			}
		}
		
	}
	
	
	public static String timeFormat(Long input) {
		String input0 = "0000000".substring(input.toString().length()) + input.toString();
		return input0.substring(1, 3) + 
				":" + (Integer.parseInt(input0.substring(3, 5))) + ":" + 
				"00".substring((Integer.parseInt(input0.substring(5, 7))+"").length()) + 
				Integer.parseInt(input0.substring(5, 7));
	}
	
	public static boolean isBetween(long time1, long time2) {
		return currentTime >= time1 && currentTime < time2;
	}
}