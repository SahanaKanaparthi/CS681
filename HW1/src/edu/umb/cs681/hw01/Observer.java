package edu.umb.cs681.hw01;
@FunctionalInterface
public interface Observer {
	
	abstract void update(Observable obs, Object obj);

}
