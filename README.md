# Java Core

* Thread & Lock & Synchronized
	* > Synchronized
		* >> for normal method->lock 'this'
		* >> for static method->lock 'class'		
	* > Wait & Notify & Sleep
		* >> wait, relese lock
		* >> notify, wake up __wait__ thread, and get lock
		* >> sleep, does NOT release lock
	* > Lock
		* >> Provide more precise synchronized control than Synchronized keyword
		* >> unlock() method must be in finally block
		* >> 		
			```java
			private Lock lock =new ReentrantLock();
			```
	* > ReadWriteLock
		* >> 	
			```java	
			class Data {      
				
			    private int data;// 共享数据  
			    
			    private ReadWriteLock rwl = new ReentrantReadWriteLock();     
			    
			    public void set(int data) {  
			    	
			        rwl.writeLock().lock();// 取到写锁  
			        
			        try {  
			            //System.out.println(Thread.currentThread().getName() + "准备写入数据");  
			            try {  
			                Thread.sleep(20);  
			            } catch (InterruptedException e) {  
			                e.printStackTrace();  
			            }  
			            this.data = data;  
			            System.out.println(Thread.currentThread().getName() + "写入" + this.data);  
			        } finally {  
			            rwl.writeLock().unlock();// 释放写锁  
			        }  
			    }     
			    
			    public void get() {  
			    	
			        rwl.readLock().lock();// 取到读锁  
			        try {  
			            //System.out.println(Thread.currentThread().getName() + "准备读取数据");  
			            try {  
			                Thread.sleep(20);  
			            } catch (InterruptedException e) {  
			                e.printStackTrace();  
			            }  
			            System.out.println(Thread.currentThread().getName() + "读取" + this.data);  
			        } finally {  
			            rwl.readLock().unlock();// 释放读锁  
			        }  
			    }  
			}  
			```			
* Collection
