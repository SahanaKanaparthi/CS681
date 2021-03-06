package edu.umb.cs681.hw08;

public class RunnableConcurrentSingleton implements Runnable {

	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());
	}

	public static void main(String[] args) {
		Thread t1=new Thread(new RunnableConcurrentSingleton());
		Thread t2=new Thread(new RunnableConcurrentSingleton());
		Thread t3=new Thread(new RunnableConcurrentSingleton());
		Thread t4 = new Thread(new RunnableConcurrentSingleton());
		
		try {
			t1.start();
			t2.start();
			t3.start();
			t4.start();
		}catch (Exception e){
	         System.out.println("Given  path is not found");
	      }
		
	}

}
