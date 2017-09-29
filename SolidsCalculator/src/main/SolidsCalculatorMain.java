package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import main.Data.*;

public class SolidsCalculatorMain {

	public static JFrame mainFrame = new JFrame("Solids Properties Calculator");
	public static JComboBox<String> comboBox = new JComboBox<>(new String[] {"Cube", "Sphere", "Cylinder", "THIS IS A MASSIVE TEXT THING"});
	public static JTextField h = new JTextField(),
							 a = new JTextField();
	public static JLabel volume = new JLabel(),
						 surfaceArea = new JLabel();

	public static void main(String[] args) {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);

		comboBox.setSize(comboBox.getPreferredSize());
		mainPanel.add(comboBox);
		
		h.setFont(new Font("Calibri", 10, 25));
		a.setFont(new Font("Calibri", 10, 25));
		
		h.setSize(comboBox.getWidth(), h.getPreferredSize().height);
		a.setSize(comboBox.getWidth(), a.getPreferredSize().height);
		
		h.setLocation(0, comboBox.getHeight());
		a.setLocation(0, comboBox.getHeight() + h.getHeight());
		
		h.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				System.out.println(arg0.getKeyChar());
				try{
					if(a.getText().matches("[0-9]*[.0-9]+")){
						if(h.getText().matches("[0-9]*[.0-9]+")){
							volume.setText(invokeMethod(comboBox.getSelectedItem().toString(), Double.parseDouble(a.getText()), Double.parseDouble(h.getText())));
						}else{
							volume.setText(invokeMethod(comboBox.getSelectedItem().toString(), Double.parseDouble(a.getText())));
						}
					}
				}catch(Exception e){
					
				}
				if(!String.valueOf(arg0.getKeyChar()).matches("[0-9.]{1}")) h.setForeground(Color.RED);
				else h.setForeground(Color.BLACK);
			}
			
		});
		
		h.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				if(h.getText().equals("Height")){
					h.setText("");
					h.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(h.getText().equals("") || !h.getText().matches("[0-9]*[.0-9]+")){
					h.setText("Height");
					h.setForeground(Color.GRAY);
				}
			}
			
		});
		a.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				if(a.getText().equals("Edge Length") || a.getText().equals("Radius")){
					a.setText("");
					a.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(a.getText().equals("") || !a.getText().matches("[0-9]*[.0-9]+")){
					a.setText("Edge Length");
					a.setForeground(Color.GRAY);
				}
			}
			
		});
		
		mainPanel.add(h);
		mainPanel.add(a);
		
		mainPanel.setSize(mainPanel.getPreferredSize());
		mainFrame.setContentPane(mainPanel);
		mainFrame.setSize(400, 200);
		mainFrame.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public static <A> A invokeMethod(String c, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{

		Class<?>[] classArgs = new Class<?>[args.length];
		for(int i = 0; i < classArgs.length; i++) classArgs[i] = args[i].getClass();

		return (A) SolidsCalculatorMain.class.getMethod(c, classArgs).invoke(args);

	}
	
	
}
