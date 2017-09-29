package MiscMain;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Voter {
	
	public static JFrame mainFrame = new JFrame("Voting Application");
	public static ArrayList<JPanel> panels = new ArrayList<>();
	public static JPanel votePanel = new JPanel(new CardLayout());
	public static int currentPanel = 0;
	
	public static JButton nextButton = new JButton("Next");
	public static JButton prevButton = new JButton("Previous");
	
	public static void main(String[] args) {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);
		mainFrame.setLayout(new GridBagLayout());	
		
		ArrayList<String> radioButton1Options = new ArrayList<>();
		radioButton1Options.addAll(Arrays.asList(new String[]{"This is Option 1", "Option 2", "This is a long option 3", "4", "Option five", "Option 6", 
																"Option 7", "This is a long option 8", "9", "Option ten", "Option 11"}));
		ArrayList<String> radioButton2Options = new ArrayList<>();
		radioButton2Options.addAll(Arrays.asList(new String[]{"This is Option 1", "Option 2", "This is a long option 3", "4"}));
		
		panels.add(new radioButtonPanel(radioButton1Options, "Radio Button Panel 1"));
		panels.add(new radioButtonPanel(radioButton2Options, "Radio Button Panel 2"));
		panels.add(new yesNoPanel("Yes/No Panel 1", true));
		
		
		for(JPanel panel : panels){
			votePanel.add(panel, panel.getName());
		}
		
		nextButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentPanel++;
				updatePanel();
			}
		});
		prevButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentPanel--;
				updatePanel();
				
			}
			
		});
		
		mainFrame.add(votePanel, gbc(0, 0, 4, 2, .5, .5, GridBagConstraints.BOTH));
		mainFrame.add(nextButton, gbc(1, 2, 1, 1, .5, .5, GridBagConstraints.NONE));
		mainFrame.add(prevButton, gbc(0, 2, 1, 1, .5, .5, GridBagConstraints.NONE));
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public static GridBagConstraints gbc(int x, int y, int gridWidth, int gridHeight, double weightx, double weighty, int fill){
		GridBagConstraints c = new GridBagConstraints();
		c.fill = fill;
		c.weightx = weightx;
		c.weighty = weighty;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		c.gridx = x;
		c.gridy = y;
		return c;
	}
	public static void updatePanel(){
		if(!(panels.get(panels.size()-1) instanceof finalizePanel)){
			panels.add(new finalizePanel());
		}
		
		if(currentPanel == panels.size()-1 && panels.get(panels.size()-1) instanceof finalizePanel){
			ArrayList<String> topics = new ArrayList<>();
			ArrayList<String> selections = new ArrayList<>();
			
			for(JPanel panel : panels){
				if(panel instanceof finalizePanel) continue;
				topics.add(panel.getName());
				selections.add(getSelected(panel));
			}
			
			updateFinalize(topics, selections, "Finalize");
			
		}else if(currentPanel >= panels.size()){
			currentPanel--;
		}else if(currentPanel <= -1){
			currentPanel++;
		}
		
		if(panels.get(currentPanel) instanceof finalizePanel){
			nextButton.setText("Cast Votes");
		}else{
			nextButton.setText("Next");
		}
		
		CardLayout cl = (CardLayout)(votePanel.getLayout());
        cl.show(votePanel, panels.get(currentPanel).getName());
		
	}
	
	public static String getSelected(JPanel panel){
		if(panel instanceof radioButtonPanel){
			return ((radioButtonPanel)panel).currentSelection;
		} else if(panel instanceof yesNoPanel){
			return ((yesNoPanel)panel).currentSelection ? "Yes" : "No";
		} else if(panel instanceof finalizePanel){
			return "Finalize Panel";
		} else{
			return "Not a voter panel";
		}
	}
	
	public static void updateFinalize(ArrayList<String> topics, ArrayList<String> selections, String panelName){
		if(panels.get(panels.size()-1) instanceof finalizePanel){
			CardLayout cl = (CardLayout)(votePanel.getLayout());
        	cl.removeLayoutComponent(panels.remove(panels.size()-1));
		}
		panels.add(new finalizePanel(topics, selections, panelName));
		votePanel.add(panels.get(panels.size()-1), panelName);
	}
}














class radioButtonPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public ButtonGroup group = new ButtonGroup();
	public JPanel radioPanel = new JPanel();
	public JLabel titleLabel = new JLabel();
	public String currentSelection = "";
	public String name = "";
	
	public radioButtonPanel(ArrayList<String> options, String title){
		super();
		setName(title);
		name = title;
		
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Sans Serif", 10, 50));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		radioPanel.setLayout(new GridLayout(options.size() / 5, (options.size() % 5) + 1));
		
		for(String option : options){
			JRadioButton button = new JRadioButton(option);
			button.setFont(new Font("Sans Serif", 10, 20));
			button.setActionCommand(option);
			button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					currentSelection = arg0.getActionCommand();
				}
				
			});
			group.add(button);
			radioPanel.add(button, i % 5, i / 5);
			i++;
		}
		
		setLayout(new GridLayout(2, 1));
		add(titleLabel);
		add(radioPanel);
		
	}

}

class yesNoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public JLabel titleLabel = new JLabel();
	public JButton yesNoButton = new JButton();
	public boolean currentSelection;
	public String name = "";
	
	public yesNoPanel(String title, boolean defaultSelection){
		super();
		setName(title);
		name = title;
		
		titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Sans Serif", 10, 50));
		
		currentSelection = defaultSelection;
		
		yesNoButton.setText(defaultSelection ? "Yes" : "No");
		yesNoButton.setBackground(defaultSelection ? Color.GREEN : Color.RED);
		yesNoButton.setSize(yesNoButton.getPreferredSize());
		yesNoButton.setMaximumSize(yesNoButton.getPreferredSize());
		yesNoButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentSelection = !currentSelection;
				yesNoButton.setBackground(currentSelection ? Color.GREEN : Color.RED);
				yesNoButton.setText(currentSelection ? "Yes" : "No");
			}
			
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		add(titleLabel, c);
		c.gridx = 0;
		c.gridy = 1;
		add(yesNoButton, c);
	}
	
}

class finalizePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public JPanel lists = new JPanel();
	public ArrayList<String> topicsList = new ArrayList<>();
	public ArrayList<String> selectionsList = new ArrayList<>();
	public String name = "";
	
	public finalizePanel(){
		super();
		name = "Unnamed Finalize";
		setName(name);
	}
	
	public finalizePanel(ArrayList<String> topics, ArrayList<String> selections, String panelName){
		super();
		setName(panelName);
		name = panelName;
		//Optional<String> longestTopic = topics.stream().sorted((e1, e2) -> e1.length() > e2.length() ? -1 : 1).findFirst();
		//Optional<String> longestSelection = selections.stream().sorted((e1, e2) -> e1.length() > e2.length() ? -1 : 1).findFirst();
		
		lists.setLayout(new GridLayout(Math.max(topics.size(), selections.size()), 2));
		topicsList = topics;
		selectionsList = selections;
		
		for(int i = 0; i < topicsList.size() && i < selectionsList.size(); i++){
			JLabel topicLabel = new JLabel(topicsList.get(i) + ":     ");
			topicLabel.setFont(new Font("Sans Serif", 10, 20));
			
			JLabel selectionLabel = new JLabel(selectionsList.get(i));
			selectionLabel.setFont(new Font("Sans Serif", 10, 20));
			
			lists.add(topicLabel);
			lists.add(selectionLabel);
		}
		
		add(lists);
	}
	
}