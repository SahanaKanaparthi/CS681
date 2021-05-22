	package edu.umb.cs681.hw10;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer{
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	
	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}
	
	public void setDone(){
		lock.lock();
		try {
			done = true;
		}
		finally {
			lock.unlock();
		}
	}

	public void generatePrimeFactors(){
		long divisor = from;
		while( dividend != 1 && divisor <= to ){
			lock.lock();
			try{
				if (done){
					System.out.println("generating factors has been stopped");
					this.factors.clear();
					break;
				}
				if( divisor > 2 && isEven(divisor)) {
					divisor++;
					continue;
				}
				if(dividend % divisor == 0) {
					factors.add(divisor);
					System.out.println(divisor);
					dividend /= divisor;
				}else {
					if(divisor==2){ 
						divisor++; 
						}
					else{ 
						divisor += 2;
						}
				}
			} finally {
				lock.unlock();
			}

			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}

	public static void main(String[] args) {
		RunnableCancellableInterruptiblePrimeFactorizer rc = new RunnableCancellableInterruptiblePrimeFactorizer(8,2, 900);
		Thread thread = new Thread(rc);
		

		try {
			thread.start();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		

		try {
			rc.setDone();
			thread.interrupt();
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("prime factors :" + rc.getPrimeFactors() );
		RunnableCancellableInterruptiblePrimeFactorizer rc1= new RunnableCancellableInterruptiblePrimeFactorizer(15,3, 900);
		Thread thread1 = new Thread(rc1);
		

		try {
			thread1.start();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		

		try {
			rc.setDone();
			thread1.interrupt();
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("prime factors :" + rc1.getPrimeFactors() );
	}
}
