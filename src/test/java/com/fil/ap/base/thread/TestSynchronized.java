package com.fil.ap.base.thread;

public class TestSynchronized {

	//synchronized, for normal method->lock 'this'
	//synchronized, for static method->lock 'class'
	
	public synchronized void m1() {
		
		System.out.println("m1 lock this......");
		
		System.out.println("m1 sleep start......");
		
		synchronized (TestSynchronized.class){
			
			System.out.println("m1 lock class......");
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}

		System.out.println("m1 sleep end......");
	}
	
	public synchronized void m2() {
		
		System.out.println("run m2 with synchronized......");
	}
	
	public void m3() {
		
		System.out.println("run m3 no synchronized......");
	}
	
	public static void m4static() {
		
		System.out.println("run m4 static no synchronized......");
	}
	
	public static void m5static() {
		
		System.out.println("run m5 static with synchronized......");
	}
	
	public static void main(String args[]) {
		
		TestSynchronized input = new TestSynchronized();
		
		new Thread(new Thread1(input)).start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		new Thread(new Thread2(input)).start();
	}
	
	private static class Thread1 implements Runnable {

		TestSynchronized input;
		
		private Thread1(TestSynchronized input) {
			
			this.input = input;
		}
		
		public void run() {
			
			System.out.println("enter thread1...");
			input.m1();
		}
	}
	
	private static class Thread2 implements Runnable {

		TestSynchronized input;
		
		private Thread2(TestSynchronized input) {
			
			this.input = input;
		}
		
		public void run() {
			
			System.out.println("enter thread2...");
			input.m3();
			TestSynchronized.m4static();
			
			synchronized (TestSynchronized.class){
				TestSynchronized.m5static();
			}
			
			input.m2();
		}
	}
}
