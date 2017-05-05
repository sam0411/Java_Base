package com.fil.ap.base.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

	private int j;
	
	private Lock lock =new ReentrantLock();
	
	public static void main(String[] args) {

		TestLock tt = new TestLock();
		
		for(int i=0;i<2;i++) {
			
			new Thread(tt.new Adder()).start();
			new Thread(tt.new Subtractor()).start();
		}
	}
	
	private class Subtractor implements Runnable {

		public void run() {
		
			while(true) {
				
				lock.lock();
				
//				synchronized (TestLock.this) {
//				System.out.println("j--="+ j--);
//				//这里抛异常了，锁能释放吗？
//				
//				if(1 == 1) throw new RuntimeException();
//				}

				try{
					System.out.println("j--="+ j--);
				}finally {
					lock.unlock();
				}
			}
		}
	}
	
	private class Adder implements Runnable {

		public void run() {
		
			while(true) {
			
				lock.lock();
				
//				synchronized (TestLock.this) {
//				System.out.println("j++="+ j++);
//				}

				try {
					System.out.println("j++="+ j++);
				}finally {
					lock.unlock();
				}
			}
		}
	}
}
