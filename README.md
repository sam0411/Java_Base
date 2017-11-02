# Java Core
test01
* Thread & Lock & Synchronized
	* > Synchronized
		* >> for normal method->lock 'this'
		* >> for static method->lock 'class'		
	* > Wait & Notify & Sleep
		* >> wait, relese lock
		* >> notify, wake up __wait__ thread, and get lock
		* >> sleep, does NOT release lock
	* > Volatile
		* >> 
			```java
				/**
				 * volatile只能保证对单次读/写的原子性
				 * 
				 * 因为long和double两种数据类型的操作可分为高32位和低32位两部分，因此普通的long或double类型读/写可能不是原子的。
				 * 因此，鼓励大家将共享的long和double变量设置为volatile类型，这样能保证任何情况下对long和double的单次读/
				 * 写操作都具有原子性。
				 * 
				 * i++其实是一个复合操作，包括三步骤：
				 * （1）读取i的值。 
				 * （2）对i加1。
				 * （3）将i的值写回内存。
				 * 
				 * volatile是无法保证这三个操作是具有原子性的，我们可以通过AtomicInteger或者Synchronized来保证+1操作的原子性。
				 */
			```
		* >> 
			```java
				/**
				 * 可见性问题主要指一个线程修改了共享变量值，而另一个线程却看不到。
				 * 
				 * 引起可见性问题的主要原因是每个线程拥有自己的一个高速缓存区——线程工作内存。
				 * 
				 * volatile关键字能有效的解决这个问题, 对volatile变量的写操作与普通变量的主要区别有两点：
				 * （1）修改volatile变量时会强制将修改后的值刷新的主内存中。
				 * （2）修改volatile变量后会导致其他线程工作内存中对应的变量值失效。
				 * 因此，再读取该变量值的时候就需要重新从读取主内存中的值。
				 * 
				 * 通过这两个操作，就可以解决volatile变量的可见性问题。 为什么会出现b=3;a=1这种结果呢？
				 * 正常情况下，如果先执行change方法，再执行print方法，输出结果应该为b=3;a=3。
				 * 相反，如果先执行的print方法，再执行change方法，结果应该是 b=2;a=1。
				 * 
				 * 那b=3;a=1的结果是怎么出来的？ 原因就是第一个线程将值a=3修改后，但是对第二个线程是不可见的，所以才出现这一结果。
				 * 如果将a和b都改成volatile类型的变量再执行，则再也不会出现b=3;a=1的结果了。
				 * 
				 */
			```
		* >> 
			```java
				/**
				 * volatile关键字就是Java中提供的另一种解决可见性和有序性问题的方案。
				 * 对于原子性，需要强调一点，也是大家容易误解的一点：对volatile变量的单次读/写操作可以保证原子性的，如long和double类型变量，
				 * 但是并不能保证i++这种操作的原子性，因为本质上i++是读、写两次操作。
				 * 
				 * 实例化一个对象其实可以分为三个步骤： 
				 * （1）分配内存空间。 
				 * （2）初始化对象。 
				 * （3）将内存空间的地址赋值给对应的引用。
				 * 
				 * 但是由于操作系统可以对指令进行重排序，所以上面的过程也可能会变成如下过程：
				 * （1）分配内存空间。 
				 * （2）将内存空间的地址赋值给对应的引用。 
				 * （3）初始化对象
				 * 
				 * 如果是这个流程，多线程环境下就可能将一个未初始化的对象引用暴露出来，从而导致不可预料的结果。因此，为了防止这个过程的重排序，
				 * 我们需要将变量设置为volatile类型的变量。
				 */
			```
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
