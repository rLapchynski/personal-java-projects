package MiscMain;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class DiceXMain {


	public static JFrame mainInterface = new JFrame("DiceX");
	public static JTextField maininput = new JTextField("Please Input Roll");
	public static JTextField result = new JTextField("Result Window");
	public static Random die = new Random();
	public static int numberOfDice;
	public static int numberOfSides;
	public static int modifierNumber;
	public static String mainText;
	public static String outPut;
	public static ArrayList<Integer> rolls = new ArrayList<Integer>();

	
	public static void frame_setup() {
		// setting layout to gridbaglayout
		mainInterface.setLayout(new GridBagLayout());
		// setting close function
		mainInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public static void component_setup() {

		GridBagConstraints c = new GridBagConstraints();

		//result setup
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0; 
		c.gridwidth = 3;
		result.setHorizontalAlignment(JTextField.CENTER);
		result.setFont(new Font("Sans Seirf", 25, 50));
		mainInterface.add(result, c);

		//maininput setup
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		maininput.setHorizontalAlignment(JTextField.CENTER); 
		mainInterface.add(maininput, c);   

		maininput.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				if(maininput.getText().trim().equals("Please Input Roll")) maininput.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(maininput.getText().trim().equals("Please Input Roll") || maininput.getText().trim().equals("")) maininput.setText("Please Input Roll");
			}

		});
	}
	
	public static String diceRoll(String text, rollType t){
		try{ numberOfDice = Integer.parseInt(mainText.substring(0, mainText.indexOf("d"))); }
		catch( Exception e ){ numberOfDice = 1; }
		
		try{ modifierNumber = Integer.parseInt(mainText.substring(text.contains("-") ? text.indexOf("-") : text.indexOf("+"))); }
		catch( Exception e ){ modifierNumber = 0; }
		
		int numSidesEnd = 	(t == rollType.drop || t == rollType.keep) ?  
								numSidesEnd = (t == rollType.drop) ? 
										text.lastIndexOf("d") : text.lastIndexOf("k"): 
								text.contains("-") ? 
										text.indexOf("-") : 
										text.contains("+") ? 
												text.indexOf("+") : text.length();
												
		numberOfSides = Integer.parseInt(mainText.substring(mainText.indexOf("d") + 1 , numSidesEnd));
		
		return (t == rollType.keep || t == rollType.drop) ? 
					kdRoll(	(text.substring(numSidesEnd+1, numSidesEnd+2).equals("l") ? 	
							text.substring(numSidesEnd, numSidesEnd+1).equals("k") ? 
								rollType.keepLow : rollType.dropLow : 
							text.substring(numSidesEnd, numSidesEnd+1).equals("d") ? 
								rollType.dropHigh : rollType.keepHigh), 
						numSidesEnd, numberOfDice, Integer.parseInt(mainText.substring(mainText.indexOf("d") + 1 , numSidesEnd))) :
					("" + (die.nextInt(((numberOfDice * numberOfSides) - numberOfDice) + 1) + numberOfDice + modifierNumber));
	}
	
	enum rollType{
		multDMod,
		oneDMod,
		multD,
		keep,
		drop,
		keepHigh,
		keepLow,
		dropHigh,
		dropLow,
		singleD;
	}
	
	public static String kdRoll(rollType t, int numSidesEnd, int numOfDice, int numberOfSides){
		System.out.println("::" + t + " " + numSidesEnd + " " + numOfDice + " " + 
							numberOfSides + " " + Integer.parseInt(mainText.substring(numSidesEnd + 2)));
		rolls.clear();
		outPut = "";
		for (int i = 0; i < numberOfDice; i++) {
			rolls.add(die.nextInt(numberOfSides)+1);
		}
		
		for (int i = 0; i < ((t == rollType.keepHigh || t == rollType.keepLow) ? numberOfDice - Integer.parseInt(mainText.substring(numSidesEnd + 2)) : Integer.parseInt(mainText.substring(numSidesEnd + 2))); i++){
			if(t.equals(rollType.dropHigh) || t.equals(rollType.dropLow)) rolls.remove(rolls.indexOf(t.equals(rollType.dropHigh) ? Collections.max(rolls) : Collections.min(rolls)));
			else if(t.equals(rollType.keepHigh) || t.equals(rollType.keepLow)) rolls.remove(rolls.indexOf(t.equals(rollType.keepHigh) ? Collections.min(rolls) : Collections.max(rolls)));
		}
		
		for (int i = 0; i < rolls.size(); i++) {
			outPut += rolls.get(i);
			if (i < rolls.size()-1) { outPut += ", "; }
			else {outPut += (" : " + rolls.stream().mapToInt(Integer::intValue).sum()); }
		}
		return outPut;
	}
	
	
	public static void actionSetup() {
		maininput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainText = maininput.getText().toLowerCase();
				
				String[] regexes = {"[0-9]+(d)[0-9]+[+|\\-][0-9]+", "(d)[0-9]+[+|\\-][0-9]+", "[0-9]+(d)[0-9]+", "[0-9]+(d)[0-9]+(kh|kl)[0-9]+", "[0-9]+(d)[0-9]+(dh|dl)[0-9]+", "(d)[0-9]+"};
				rollType[] rolls = {rollType.multDMod, rollType.oneDMod, rollType.multD, rollType.keep, rollType.drop, rollType.singleD};
				
				for(int i = 0; i < regexes.length; i++){
					if(mainText.matches(regexes[i])){
						result.setText(diceRoll(mainText, rolls[i]));
						result.setFont(new Font("Sans Seirf", 10, 75 - (int) Math.log(result.getText().length())*13 ));
						break;
					} else if(i == regexes.length-1) result.setText("Error Parsing");
				}
				
			}
		});
	}
	public static void main(String[] args) {
		frame_setup();
		component_setup();
		actionSetup();
		result.setEditable(false);
		mainInterface.pack();
		mainInterface.setVisible(true);
		mainInterface.setSize(700,500);
		mainInterface.setLocationRelativeTo(null);
	}

}