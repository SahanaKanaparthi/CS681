package edu.umb.cs681.hw01;

import java.util.LinkedList;
public abstract class Observable {
	LinkedList<Observer>observers=new LinkedList<Observer>();
	boolean change;
	public Observable() {
		super();
	}

	public void addObserver(Observer o){
		observers.add(o);
		//System.out.println(o);
		this.notifyObservers(o);
	}
	
	public void deleteObserver(Observer o) {
		observers.remove(o);
		//notifyObservers(observers);
	}
	protected void setChanged() {
		change=true;
	}
	
	public boolean hasChanged() {
		if(change==true) {
			return true;
		}
		return false;
	}
	
	protected void clearChanged() {
		change=false;
	}
	
	void notifyObservers() {
		
	}
	
	public void notifyObservers(Object obj) {
		if(hasChanged()) {
			for(Observer o:observers) {
				o.update(this, obj);
				System.out.println("Notified");
			}
			clearChanged();
		}
	}
}
