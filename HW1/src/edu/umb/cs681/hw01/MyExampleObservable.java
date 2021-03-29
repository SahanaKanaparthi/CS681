package edu.umb.cs681.hw01;

public class MyExampleObservable {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StockQuoteObservable stockObserver = new StockQuoteObservable();
		
		stockObserver.addObserver((Observable o,Object obj)->{System.out.println("Added new Observer for Stock");});
		
		stockObserver.changeQuote("SAH", 2000);
		

		DJIAQuoteObservable djiaObserver = new DJIAQuoteObservable();
		
		djiaObserver.addObserver((Observable o,Object obj)->{System.out.println("Added new Observer for DJIA");});

		djiaObserver.changeQuote("RAH", 9000);
		
		}

}
