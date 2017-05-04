package com.fil.ap.base.thread;

public class WaitNotify {

	public static void main(String[] args) {
		
		new Thread(new Thread1()).start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		new Thread(new Thread2()).start();
	}
	
	private static class Thread1 implements Runnable {

		public void run() {
			
			//由于这里的 Thread1和下面的 Thread2内部 run 方法要用同一对象作为监视器，我们这里
			//不能用 this，因为在 Thread2里面的 this 和这个 Thread1的 this 不是同一个对象。我们用
			//MultiThread.class 这个字节码对象，当前虚拟机里引用这个变量时，指向的都是同一个对象。
			synchronized (WaitNotify.class){

				try {
					System.out.println("enter thread1...");
					System.out.println("thread1 is waiting");
					
					// 释放锁有两种方式，第一种方式是程序自然离开监视器的范围，也就是离开
					// 了 synchronized 关键字管辖的代码范围，另一种方式就是在 synchronized 关键字管辖的代
					// 码内部调用监视器对象的 wait 方法。这里，使用 wait 方法释放锁。
					WaitNotify.class.wait();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				System.out.println("thread1 is going on...");
				System.out.println("thread1 is being over!");
			}
		}
	}
	
	private static class Thread2 implements Runnable {

		public void run() {

			System.out.println("enter thread2 out of synchronized...");
			
			synchronized (WaitNotify.class){
				
				System.out.println("enter thread2...");
				System.out.println("thread2 notify other thread can release wait status..");
				
				//由于 notify 方法并不释放锁，即使 thread2调用下面的 sleep 方法休息了10毫秒，但 thread1
				//仍然不会执行，因为 thread2没有释放锁，所以 Thread1无法得不到锁
				
				WaitNotify.class.notify();
				
				System.out.println("thread2 is sleeping ten millisecond...");
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				
				System.out.println("thread2 is going on...");
				System.out.println("thread2 is being over!");
			}
			
		}
	}
}
