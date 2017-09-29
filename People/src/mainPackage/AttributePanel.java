package mainPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class AttributePanel extends JPanel implements Actionable{
	
	private static final long serialVersionUID = 1L;
	
	private EditableLabel nameLabel;
	private EditableLabel valueLabel;
	private Double weightx;
	private Attribute<?> attribute;
	private boolean nameEditable = true;
	private boolean valueEditable = true;
	private boolean initialized = false;
	private boolean showName = true;
	private ArrayList<ActionListener> actionListenerList;
	private ActionListener listener;
	
	public AttributePanel(Attribute<?> a){
		super();
		
		setListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				processEvent(e);
				updateToAttribute();
			}
			
		});
		
		setAttribute(a);
		
		setLayout(new GridBagLayout());
		
		setNameLabel(new EditableLabel(a.getName() + "  "));
		setValueLabel(new EditableLabel(a.getValue().toString()));
		
		getValueLabel().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!e.getActionCommand().split(":")[0].equals("TYPING"))
					getAttribute().setValue(getValueLabel().getContent());
				updateToAttribute();
			}
			
		});
		
		GridBagConstraints g = new GridBagConstraints();
		
		g.gridx = 0;
		g.gridy = 0;
		add(getNameLabel(), g);
		
		g.gridx = 1;
		g.gridy = 0;
		g.weightx = getWeightx();
		add(getValueLabel(), g);
		
		this.initialized = true;
	}

	public EditableLabel getNameLabel() {
		return nameLabel;
	}
	public void setNameLabel(EditableLabel jLabel) {
		this.nameLabel = jLabel;
	}
	public EditableLabel getValueLabel() {
		return valueLabel;
	}
	public void setValueLabel(EditableLabel valueLabel) {
		this.valueLabel = valueLabel;
	}
	public Double getWeightx() {
		if(weightx == null){
			return 0.1;
		}
		return weightx;
	}
	public void setWeightx(Double weightx) {
		this.weightx = weightx;
	}
	public Attribute<?> getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute<?> attribute) {
		if(initialized) getAttribute().removeActionListener(getListener());
		this.attribute = attribute;
		getAttribute().addActionListener(getListener());
	}
	public boolean isNameEditable() {
		return nameEditable;
	}
	public void setNameEditable(boolean nameEditable) {
		this.nameEditable = nameEditable;
	}
	public boolean isValueEditable() {
		return valueEditable;
	}
	public void setValueEditable(boolean valueEditable) {
		this.valueEditable = valueEditable;
	}
	public boolean isShowName() {
		return showName;
	}
	public void showName(boolean showName) {
		nameLabel.setVisible(showName);
		this.showName = showName;
	}
	public void setLabelsFont(Font font){
		getNameLabel().setFont(font);
		getValueLabel().setFont(font);
	}
	public void updateToAttribute(){
		if(getAttribute() != null && getAttribute().initialized()){
			getNameLabel().setContent(getAttribute().getName());
			getValueLabel().setContent(getAttribute().getValue().toString());
		} else {
			
		}
	}
	public void setFont(Font font){
		if(getNameLabel() != null) getNameLabel().setFont(font);
		if(getValueLabel() != null) getValueLabel().setFont(font);
	}
	public ActionListener getListener() {
		return listener;
	}
	public void setListener(ActionListener listener) {
		this.listener = listener;
	}
	
	//Action Event ----------------------------------------------------------------------------------------------------------------------------------
	public synchronized void addActionListener(ActionListener listener) {
		if(actionListenerList == null) {
			actionListenerList = new ArrayList<ActionListener>(2);
		}
		if(!actionListenerList.contains(listener)) {
			actionListenerList.add(listener);
		}
	}
	public synchronized void removeActionListener(ActionListener listener) {
		if(actionListenerList != null && actionListenerList.contains(listener)) {
			actionListenerList.remove(listener);
		}
	}
	private void processEvent(ActionEvent e) {
		ArrayList<?> list;
		synchronized(this) {
			if(actionListenerList == null) return;
			list = (ArrayList<?>)actionListenerList.clone();
		}
		for(int i = 0; i < list.size(); i++) {
			ActionListener listener = (ActionListener)list.get(i);
			listener.actionPerformed(e);
		}
	}
	
}
