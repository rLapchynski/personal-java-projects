package mainPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PeopleMain {
	public static JFrame mainFrame = new JFrame("People");
	
	public static void main(String[] args) {
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		
		for(int i = 0; i < 10; i++){
			gbc.gridy = i;
			Attribute<?> a = new Attribute<>("Attribute " + i, "" + i);
			
			a.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					 System.out.println("MAIN: " + e.getActionCommand());
				}
				
			});
			
			
			
			//updater.add(a.getPanel());
			
			mainFrame.add(new AttributePanel(a), gbc);
			
		}
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}
