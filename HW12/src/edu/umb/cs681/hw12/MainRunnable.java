package edu.umb.cs681.hw12;

public class MainRunnable implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Customer cust = new Customer(new Address("25 hecla strt", "Nashua", "NH", 2198));
		System.out.println("Current Address: " + cust.getAddress());
		cust.setAddress(cust.getAddress().change("50 harbor pont", "Boston", "MA", 5984));
		System.out.println(" New Address: "+ cust.getAddress());
		
		cust.setAddress(new Address("137 staurt St", "Boston", "MA", 6655));
        System.out.println(" Changed address to "+ cust.getAddress());
		
	}
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new MainRunnable());
		
		
		thread1.start();

		
		try {
			thread1.join();

		}catch (InterruptedException err) {
			err.printStackTrace();
		}	
	}
}
