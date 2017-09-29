package MiscMain;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


public class Prog_RollerCoaster {
	
	public static JFrame mainFrame = new JFrame("Roller Coaster Speed Calculator");
	public static GraphPanel mainPanel = new GraphPanel();
	public static JTextField interval = new JTextField("Interval"), 
							 totalTime = new JTextField("Total Time"), 
							 mass = new JTextField("Mass"), 
							 rollingResistance = new JTextField("Rolling Resistance");

	public static void main(String[] args) {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setResizable(true);
		mainFrame.setSize(400, 400);
		
		ActionListener e = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Coord> points = new ArrayList<>();
				int a = 0;
				for(Double i : sampling(Double.parseDouble(interval.getText()), 
										Double.parseDouble(totalTime.getText()), 
										Double.parseDouble(mass.getText()), 
										Double.parseDouble(rollingResistance.getText()))){
					points.add(new Coord(a, i.intValue()));		
				}
				mainPanel.setPointsList(points);
			}
			
		};

		interval.addActionListener(e);
		totalTime.addActionListener(e);
		mass.addActionListener(e);
		rollingResistance.addActionListener(e);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		//mainPanel.setLocation(0, 0);
		mainPanel.setSize(400,  300);
		mainFrame.add(mainPanel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		mainFrame.add(interval, c);
		c.gridy = 2;
		mainFrame.add(totalTime, c);
		c.gridy = 3;
		mainFrame.add(mass, c);
		c.gridy = 4;
		mainFrame.add(rollingResistance, c);
		
		//mainFrame.pack();
		mainFrame.setVisible(true);
	}
	/**
	 * @param tTS In seconds, timeToSample
	 * @param tOC In seconds, timeOfCoaster
	 * @param mOC In kilograms, mass of coaster
	 * @param RRC Rolling Resistance Coefficient. Google it if you want to change value
	 */
	@SuppressWarnings("unused")
	public static ArrayList<Double> sampling( double tTS, double tOC, double mOC, double RRC){
		
		ArrayList<Double> samples = new ArrayList<Double>(); 	//Stores samples.
		double g = 9.81; 										//Force of gravity, in m/s
		double vAP = 0; 										//velocityAtPoint
		double rOC = RRC * mOC * g; 							//resistance on Rollercoaster, in newtons
		double fF = mOC * g; 									//forwardForce
		double tF = fF - rOC; 									//totalForce
		double accel = tF / mOC;						 		//total acceleration
																//Force of gravity in N/s is 9.81 N/kg	
		//
		/*
		 * Object has 'weight' of mOC * g acting forwards
		 * Object has drag of rOC acting backwards
		 * 
		 * Forward force - backwards force = total force
		 * Acceleration from newtons == force/mass
		 * 
		 * 
		 *  for(double i = tTS; i <= tOC; i += tTS ){ //Calculates samples for main 'hill'.
		 *  	vAP += (g*tTS); //Assumes no friction. Fix?
		 *  	samples.add( vAP );
		 *  }
		 */
		
		for(double i = tTS; i <= tOC; i += tTS ){ 				//Calculates samples for main 'hill'.
			vAP += (accel*tTS); 								//Assumes no friction. Fix?
			samples.add( vAP );
		}
		
		for(Double d:samples){ 									//Debug
			//System.out.println(d + "");
		}
		return samples; 										//Values returned in m/s
	}
	/**
	 * Overloaded method
	 * @param tTs In seconds, timeToSample
	 * @param tOC In seconds, timeOfCoaster
	 * @param mOC In kilograms, mass of coaster
	 * RRC is set to 0.005
	 */
	public static ArrayList<Double> sampling( double tTS, double tOC, double mOC){
		return sampling(tTS, tOC, mOC, 0.005);
	}

}

class GraphPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//Instance variables
	private ArrayList<Coord> pointsList = new ArrayList<>();
	private boolean connected = true;
	
	//Constructors
	public GraphPanel(){
		super();
		setBackground(Color.LIGHT_GRAY);
	}
	public GraphPanel(ArrayList<Coord> points){
		super();
		pointsList = points;
		setBackground(Color.LIGHT_GRAY);
	}
	public GraphPanel(ArrayList<Coord> points, boolean pointsConnected){
		super();
		connected = pointsConnected;
		pointsList = points;
		setBackground(Color.LIGHT_GRAY);
	}

	//Getters and setters
	public ArrayList<Coord> getPointsList() {
		return pointsList;
	}
	public void setPointsList(ArrayList<Coord> pointsList) {
		this.pointsList = pointsList;
	}
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        ArrayList<Coord> points = getPointsList();
        if(points.size() > 0){
	        Coord prevPoint = points.get(0);
	        for(int i = 1; i < points.size(); i++){
	        	Coord point = points.get(i);
	        	g.drawLine((int)prevPoint.X, (int)prevPoint.Y, (int)point.X, (int)point.Y);
	        	prevPoint = point;
	        }
        }
    }
}
/**
 * A simple class for storing and working with coordinates.
 */
class Coord implements Comparable<Coord>{
	public double X, Y;
	public Coord(){
		initialize(0, 0);
	}
	public Coord(int x, int y){
		initialize(x, y);
	}
	public Coord(double x, double y){
		initialize(x, y);
	}
	private void initialize(double x, double y){
		X = x;
		Y = y;
	}
	public Coord clone(){
		return new Coord(X, Y);
	}
	public double distanceTo(Coord c){
		return Math.sqrt(Math.pow(X - c.X, 2) + Math.pow(Y - c.Y, 2));
	}
	public void moveHoriz(double d){
		X += d;
	}
	public void moveVert(double d){
		Y += d;
	}
	public String toString(){
		return "(" + X + ", " + Y + ")";
	}
	public boolean equals(Coord c){
		return Y == c.Y && X == c.X;
	}
	public int compareTo(Coord c) {
		return ((Double)c.X).compareTo(this.X);
	}
}