package mainPackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Buttons {
	public static JButton runGameButton(){
        
		JButton runGameButton = new JButton("");
        runGameButton.setSize(Frames.scale(new Dimension(200, 40)));
        runGameButton.setLocation(Frames.scale(new Dimension(5,265)).width, Frames.scale(new Dimension(5,265)).height);
        runGameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//System.out.println(_GamesMainClass.gameIdentifiers.get(_GamesMainClass.currentGameSelected));
        		Games.runGame(_GamesMainClass.gameIdentifiers.get(_GamesMainClass.currentGameSelected)
        				.substring((_GamesMainClass.gameIdentifiers.get(_GamesMainClass.currentGameSelected).indexOf("[")+1), 
        						_GamesMainClass.gameIdentifiers.get(_GamesMainClass.currentGameSelected).indexOf("]")));
        		_GamesMainClass.mainFrame.dispose();
        		//System.out.println("Run " + _GamesMainClass.runGameButton.getText().substring(4));
        	}
        });

        return runGameButton;
	}
	public static JButton gameTypeButton(String btnText, ImageIcon previewImage, int gameNum){
		JButton gameTypeButton = new JButton(btnText);
        gameTypeButton.setSize(Frames.scale(new Dimension(200, (int)(gameTypeButton.getPreferredSize().height*1.5))));
        gameTypeButton.setLocation(Frames.scale(new Dimension(5,_GamesMainClass.gamesBtnsY)).width, Frames.scale(new Dimension(5,_GamesMainClass.gamesBtnsY)).height);
        gameTypeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		_GamesMainClass.previewLabel.setIcon(previewImage);
        		_GamesMainClass.previewLabel.setSize(_GamesMainClass.previewLabel.getIcon().getIconWidth(),
        				_GamesMainClass.previewLabel.getIcon().getIconHeight());
        		_GamesMainClass.runGameButton.setText("Run " + btnText);
        		_GamesMainClass.runGameButton.setEnabled(true);
        		_GamesMainClass.currentGameSelected = gameNum;
        	}
        });
        _GamesMainClass.gamesBtnsY += gameTypeButton.getPreferredSize().height*1.5 + 5;
        
        return gameTypeButton;
	}
	public static JButton fullscreenToggleButton(JFrame frame, int funcToCallID){
		JButton fullscreenToggle = new JButton(FileUtil.fullscreenIcon);
        fullscreenToggle.setSize(Frames.scale(new Dimension(20,20)));
        fullscreenToggle.setLocation(Frames.stdDim.width - (2 * FileUtil.fullscreenIcon.getIconWidth() + 5), 5);
        fullscreenToggle.setOpaque(false);															//gets rid of everything except for the frickin one pixel border 
        fullscreenToggle.setBorder(null);															//
        fullscreenToggle.setContentAreaFilled(false);												//
        fullscreenToggle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Frames.setFull(frame);
        		fullscreenToggle.setLocation(frame.getSize().width - (2 * FileUtil.fullscreenIcon.getIconWidth() + 5), 5);
        		if(!Frames.currentlyFullScreen){ 												//Not is necessary because setFull() changes it
        			fullscreenToggle.setIcon(FileUtil.exitFullscreenIcon);
        		}
        		else{
        			fullscreenToggle.setIcon(FileUtil.fullscreenIcon);
        		}
        		
        	}
        });
        return fullscreenToggle;
	}
	public static JLabel  teamNameLabel(JFrame frame, String initText, int initX, int initY){
		JLabel teamNameLabel = new JLabel(initText);
		teamNameLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("MouseClicked");
				String newText = ((String)JOptionPane.showInputDialog(
						frame,
						"Type a new team name", 
						"Set Team Name",
						JOptionPane.PLAIN_MESSAGE));
				if(newText != null && (newText.length() > 0)){
					//System.out.println("SetNewText");
					teamNameLabel.setText(newText);
					teamNameLabel.setSize(teamNameLabel.getPreferredSize());
				}
				else if(newText != null && !(newText.length() > 0)){
					JOptionPane.showMessageDialog(frame, "Invalid Team Name");
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

        });
		teamNameLabel.setLocation(initX, initY);
		return teamNameLabel;
	}
	public static JButton closeButton(JFrame frameToClose, int initX, int initY){
		JButton closeBtn = new JButton();
		closeBtn.setIcon(FileUtil.closeButton);
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameToClose.dispatchEvent(new WindowEvent(frameToClose, WindowEvent.WINDOW_CLOSING));
			}});
		closeBtn.setOpaque(false);															//gets rid of everything except for the frickin one pixel border 
		closeBtn.setBorder(null);															//
		closeBtn.setContentAreaFilled(false);	
		closeBtn.setLocation(initY, initX);
		closeBtn.setSize(FileUtil.closeButton.getIconWidth(), FileUtil.closeButton.getIconHeight());
		return closeBtn;
	}
}
