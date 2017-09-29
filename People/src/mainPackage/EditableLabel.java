package mainPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class EditableLabel extends JPanel implements Actionable{
	private static final long serialVersionUID = 1L;
	
	private String content;
	private boolean inLineCapable = true;
	private JLabel contentLabel;
	private JTextField editField;
	private Font font;
	private boolean editable = true;
	private ArrayList<ActionListener> actionListenerList;

	public EditableLabel(String content){
		super();
		initialize(content, true);
	}
	public EditableLabel(String content, boolean isInLineCapable){
		super();
		initialize(content, isInLineCapable);
	}
	private void initialize(String content, boolean isInLineCapable){
		setInLineCapable(isInLineCapable);
		setContentLabel(new JLabel(content));
		setEditField(new JTextField());
		setContent(content);
		
		getEditField().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c = (CardLayout)getLayout();
				getContentLabel().setText(getEditField().getText());
				c.show(getThis(), "VIEW");
				processEvent(new ActionEvent(getThis(), ActionEvent.ACTION_PERFORMED, getEditField().getText()));
			}
			
		});
		getEditField().getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				resize();
			}
			public void removeUpdate(DocumentEvent e) {
				resize();
			}
			public void insertUpdate(DocumentEvent e) {
				resize();
			}

			public void resize() {
				getEditField().setSize(getEditField().getPreferredSize());
				getThis().setSize(getThis().getPreferredSize());
				processEvent(new ActionEvent(getThis(), ActionEvent.ACTION_PERFORMED, "TYPING:" + getEditField().getText()));
			}
		});
		setLayout(new CardLayout());
		add(getContentLabel(), "VIEW");
		add(getEditField(), "EDIT");
		
		contentLabel.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent m) {
				if(!isEditable()) return;
				CardLayout c = (CardLayout)getLayout();
				getEditField().setText(getContentLabel().getText());
				c.show(getThis(), "EDIT");
				getEditField().requestFocus();
			}
			@Override
			public void mouseEntered(MouseEvent m) {
			}
			@Override
			public void mouseExited(MouseEvent m) {
			}
			@Override
			public void mousePressed(MouseEvent m) {
			}
			@Override
			public void mouseReleased(MouseEvent m) {
			}
			
		});
	
	}
	
	public boolean isInLineCapable() {
		return inLineCapable;
	}
	public void setInLineCapable(boolean inLineCapable) {
		this.inLineCapable = inLineCapable;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		contentLabel.setText(content);
	}
	public JLabel getContentLabel() {
		return contentLabel;
	}
	public void setContentLabel(JLabel contentLabel) {
		this.contentLabel = contentLabel;
	}
	public JTextField getEditField() {
		return editField;
	}
	public void setEditField(JTextField editField) {
		this.editField = editField;
	}
	public void setFont(Font font){
		super.setFont(font);
		if(!(getContentLabel() == null)){
			getContentLabel().setFont(font);
		}
		if(!(getEditField() == null)){
			getEditField().setFont(font);
		}
		this.font = font;
	}
	public Font getFont(){
		return this.font;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	private EditableLabel getThis(){
		return this;
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
