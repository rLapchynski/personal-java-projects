package mainPackage;

import java.awt.event.*;
import java.util.ArrayList;

public class Attribute<T> implements Comparable<Attribute<?>>, Actionable{
	private T value;
	private String name;
	private int importance;
	private AttributeType type;
	private ArrayList<ActionListener> actionListenerList;
	
	//Constructors ----------------------------------------------------------------------------------------------------------------------------------
	public Attribute(){
		setValue("Unassigned");
		setName("Unassigned");
	}
	public Attribute(T value){
		setName("Unassigned");
		setValue(value);
	}
	public Attribute(String name){
		setName(name);
		setValue("Unassigned");
	}
	public Attribute(String name, T value){
		setName(name);
		setValue(value);
	}
	
	//Getters and Setters ---------------------------------------------------------------------------------------------------------------------------
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Value Changed"));
	}
	public String getName() {
		if(name == null){
			return "Name Unassigned";
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Name Changed"));
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public AttributeType getType() {
		return type;
	}
	public void setType(AttributeType type) {
		this.type = type;
	}
	public boolean initialized(){
		return getValue() != null && getName() != null;
	}
	
	@SuppressWarnings("unchecked")
	public void setValue(String value){
		setValue((T) value);
	}
	@SuppressWarnings("unchecked")
	public void setValue(Integer value){
		if(getValue() instanceof Integer)
			setValue((T) value);
		else{
			try{
				setValue((T) value);
			}catch(Exception e){
				throw new ClassCastException("Cannot cast from Integer to " + getValue().getClass().getSimpleName());
			}
			
		}
	}
	@SuppressWarnings("unchecked")
	public void setValue(Double value){
		if(getValue() instanceof Double)
			setValue((T) value);
		else{
			try{
				setValue((T) value);
			}catch(Exception e){
				throw new ClassCastException("Cannot cast from Double to " + getValue().getClass().getSimpleName());
			}
		}
	}
	
	public void setNameNoEvent(String name){
		this.name = name;
	}
	@SuppressWarnings("unchecked")
	public void setValueNoEvent(String value){
		this.value = (T) value;
	}
	
	//Overridden methods ----------------------------------------------------------------------------------------------------------------------------
	public String toString(){
		System.out.println(getName() + " " + getValue());
		return getName() + ": " + getValue().toString();
	}
	public boolean equals(Attribute<T> a){
		if( !a.getValue().getClass().equals( this.getValue().getClass() ) ) return false;
		return a.getName().equals( this.getName() ) && a.getValue().equals( this.getValue() );
	}
	public int compareTo(Attribute<?> a) {
		return new Integer( a.getImportance() ).compareTo( this.getImportance() );
	}
	
	//New methods -----------------------------------------------------------------------------------------------------------------------------------	
	public boolean equalsValue(Attribute<T> a){
		if( !a.getValue().getClass().equals( this.getValue().getClass() ) ) return false;
		return a.getValue().equals( this.getValue() );
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
