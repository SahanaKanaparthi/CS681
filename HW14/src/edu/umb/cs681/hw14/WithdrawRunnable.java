package edu.umb.cs681.hw14;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable  implements Runnable {
	private ThreadSafeBankAccount BA = null;
	public AtomicBoolean done = new AtomicBoolean(false);
	private ReentrantLock lock = new ReentrantLock();
	
	
	public WithdrawRunnable(ThreadSafeBankAccount BA) {
		this.BA = BA;
	}
	public void setDone() {
		done.getAndSet(true);
	}
	
	@Override
	public void run() {
		
		while (true) {
			lock.lock();
			try {
			
			if(done.get()) {
				System.out.println(Thread.currentThread().getName() +"-> Money is withdrawn successfully");
    			break;
			}
			}finally {
				lock.unlock();
			}
			
			System.out.println(Thread.currentThread().getName() +"-> Money is being withdrawn");
			BA.withdraw(400);
			
			try {
				Thread.sleep(5000);
			}catch(InterruptedException e) {
				System.out.println(e);
				continue;
			}
		}
	}
}
