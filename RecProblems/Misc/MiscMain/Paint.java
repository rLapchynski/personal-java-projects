package MiscMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Paint {
	public static JFrame mainFrame = new JFrame("Painter Thing");
	public static paintPanel mainPanel = new paintPanel();
	public static JComboBox<String> toolPicker = new JComboBox<>(new String[] {"Line", "Free Draw"});
	public static Double scrollWheelPos = 0.0;
	public static MenuBar menuBar = new MenuBar();
	
	public static void main(String[] args) {
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);
		mainFrame.setSize(600, 600);
		mainFrame.setLayout(null);		
		
		toolPicker.setLocation(0, 0);
		toolPicker.setSize(toolPicker.getPreferredSize());
		mainFrame.add(toolPicker);
		
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setLocation(0, toolPicker.getHeight());
		mainFrame.add(mainPanel);
		
		toolPicker.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchTool(toolPicker.getSelectedItem().toString());
			}
			
		});
		
		mainFrame.addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) {
		        mainPanel.setSize(mainFrame.getContentPane().getSize()); 
		    }

			@Override
			public void componentHidden(ComponentEvent arg0) {
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
			}
		});
		mainFrame.setVisible(true);
	}
	
	public static void switchTool(String toolName){
		for(int i = 0; i < mainPanel.tools.size(); i++){
			if(mainPanel.tools.get(i).getName().equals(toolName))
				mainPanel.currentTool = mainPanel.tools.get(i);
		}
	}
}

/**
 * JPanel specifically for drawing on and stuff
 */
class paintPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public line currLine = new line();
	public ArrayList<line> drawnLines = new ArrayList<>(); 
	public boolean currentlyDrawing = false;
	public ArrayList<Tool> tools = new ArrayList<>();
	public Tool currentTool;
	
	public paintPanel(){
		super();
		initializeTools();
		initializeListeners();
		currentTool = tools.get(0);
	}
	/**
	 * Initializes the listeners for the PaintPanel
	 */
	private void initializeListeners(){
		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent m) {
				currentTool.pressed(m);				
			}
			@Override
			public void mouseReleased(MouseEvent m) {
				currentTool.released(m);				
			}
		});
		addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent m) {
				currentTool.dragged(m);
				
			}
			@Override
			public void mouseMoved(MouseEvent m) {
			}
		});
		addMouseWheelListener(new MouseWheelListener(){
			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				Paint.scrollWheelPos += arg0.getWheelRotation()/2.0;
				System.out.println(Paint.scrollWheelPos);
			}
		});
	}
	
	/**
	 * Thunk to initialize the list of tools to be referenced by other methods
	 */
	public void initializeTools(){
		tools.add(new Tool(){

			@Override
			public String getName() {
				return "Line";
			}

			@Override
			public void pressed(MouseEvent m) {
				currLine = new line(m.getX(), m.getY());
			}

			@Override
			public void released(MouseEvent m) {
				currentlyDrawing = false;
				if(!(currLine.startX == 0 || currLine.startY == 0))
					drawnLines.add(currLine);
			}

			@Override
			public void dragged(MouseEvent m) {
				currLine.endX = m.getX();
				currLine.endY = m.getY();
				drawnLines.add(currLine);
				repaint();
			}

			@Override
			public void scrolled(MouseEvent m) {
			}
			
		});
		tools.add(new Tool(){

			@Override
			public String getName() {
				return "Free Draw";
			}

			@Override
			public void pressed(MouseEvent m) {
				currentlyDrawing = true;
				currLine = new line(m.getX(), m.getY());
			}

			@Override
			public void released(MouseEvent m) {
				currentlyDrawing = false;
				if(!(currLine.startX == 0 || currLine.startY == 0))
					drawnLines.add(currLine);
			}

			@Override
			public void dragged(MouseEvent m) {
				if(currentlyDrawing)
					currLine = new line(currLine.endX, currLine.endY, m.getX(), m.getY());
				else
					currLine = new line(m.getX(), m.getY(), m.getX(), m.getY());
				if(!(currLine.startX == 0 && currLine.startY == 0)){
					repaint();
					drawnLines.add(currLine);
				}
			}

			@Override
			public void scrolled(MouseEvent m) {
			}
			
		});
	}
	
	/**
	 * For drawing the line(s) when the panel needs updated
	 */
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.BLACK);
        
        for(line l : drawnLines)
        	g2.drawLine(l.startX, l.startY, l.endX, l.endY);
        
        g2.drawLine(currLine.startX, currLine.startY, currLine.endX, currLine.endY);
        System.out.println(currLine);
    }
}
/**
 * A class for specifying what a tool should do for each event
 */
abstract class Tool implements drawer{
	public String getName(){
		return "Unnamed Tool";
	}
	public abstract void pressed(MouseEvent m);
	public abstract void released(MouseEvent m);
	public abstract void dragged(MouseEvent m);
	public abstract void scrolled(MouseEvent m);
}

/**
 * Just a quick class to make it easier to pass lines between methods: one variable rather than 4
 */
class line {
	public int startX, startY, endX, endY;
	public line(int sX, int sY, int eX, int eY){
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
	}
	public line(int sX, int sY){
		startX = sX;
		startY = sY;
		endX = 0;
		endY = 0;
	}
	public line(){     }
	public boolean equals(line l){
		return l.startX == startX && l.startX == startX && l.endX == endX && l.endY == endY;
	}
	public boolean endClose(int x, int y){
		int closeThreshold = 10;
		return (Math.abs(startX - x) <= closeThreshold && Math.abs(startY - y) <= closeThreshold) || (Math.abs(endX - x) <= closeThreshold && Math.abs(endY - y) <= closeThreshold);
	}
	public String toString(){
		return startX + "," + startY + "  " + endX + "," + endY;
	}
}
/**
 * An interface for something that has to react to mouse events, specifically drag and drop type events
 */
interface drawer{
	void pressed(MouseEvent m);
	void released(MouseEvent m);
	void dragged(MouseEvent m);
	void scrolled(MouseEvent m);
}