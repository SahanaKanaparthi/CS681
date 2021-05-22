package edu.umb.cs681.hw15;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl {
	private int visitors = 0;
	private ReentrantLock lock = new ReentrantLock();
	
	private Condition sufficientCondition = lock.newCondition();
	private Condition LimitCondition = lock.newCondition();
	
	public void enter() {
		
		lock.lock();
				
		try {
			
			System.out.println(Thread.currentThread().getName() + "->  Current Visitors	: " + visitors);
			
			while (visitors >= 7) {
				try {
					System.out.println(Thread.currentThread().getName() + " Visitors exceeded");
					sufficientCondition.await();
				} catch (InterruptedException e) {
					return;
				}
			}
			
			visitors++;
			System.out.println(Thread.currentThread().getName() + "-> Set of visitors who are currently visiting: " + visitors);
			LimitCondition.signalAll();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			lock.unlock();
		}
	}
	
	public void exit() {
		
		lock.lock();
		
		try {
			System.out.println(Thread.currentThread().getName() + "->  Current Visitors: " + visitors);
			
			while (visitors <= 0) {
				try {
					System.out.println(Thread.currentThread().getName() + "-> Done visiting.");
					LimitCondition.await();
				} catch (InterruptedException e) {
					return;
				}
			}
			
			visitors--;
			System.out.println(Thread.currentThread().getName() + "-> Set of visitors who are currently visiting: " + visitors);
			sufficientCondition.signalAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public int visitorsCount() {
		lock.lock();
		try {
			return visitors;
		}
		finally {
			lock.unlock();
		}
	}
}
