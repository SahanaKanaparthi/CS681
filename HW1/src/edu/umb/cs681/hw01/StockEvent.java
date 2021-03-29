package edu.umb.cs681.hw01;

public class StockEvent {
	
	private String ticker;
	private float quote;
	public StockEvent(String s, float q) {
		// TODO Auto-generated constructor stub
		this.ticker=s;
		this.quote=q;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public float getQuote() {
		return quote;
	}
	public void setQuote(float quote) {
		this.quote = quote;
	}

}
