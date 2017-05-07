package com.fil.ap.base.thread;

public class VolatileVisible {
	
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
    volatile int a = 1;
    volatile int b = 2;

    public void change(){
    	
        a = 3;
        b = a;
    }

    public void print(){
    	
    	if(a == 1 && b == 3) System.out.println("b="+b+";a="+a);
    }

    public static void main(String[] args) {
    	
        while (true){
        	
            final VolatileVisible test = new VolatileVisible();
            
            new Thread(new Runnable() {

                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {

                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }
    }
}
