package edu.umb.cs681.hw01;
import java.util.HashMap;
import java.util.Map;
public class StockQuoteObservable  extends Observable  {
	
	private Map<String,Float> map=new HashMap<String, Float>();
	public void changeQuote(String s,float q) {
		map.put(s, q);
		setChanged();
		notifyObservers(new StockEvent(s,q));
	}
	public Map<String, Float> getMap() {
		return map;
	}
	public void setMap(Map<String, Float> map) {
		this.map = map;
	}

}
