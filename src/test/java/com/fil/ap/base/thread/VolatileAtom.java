package com.fil.ap.base.thread;

public class VolatileAtom {
	
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
    volatile int i;

    public void addI(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
    	
        final  VolatileAtom test01 = new VolatileAtom();
        
        for (int n = 0; n < 1000; n++) {
        	
            new Thread(new Runnable() {

                public void run() {
                	
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    test01.addI();
                }
            }).start();
        }

        Thread.sleep(10000);//等待10秒，保证上面程序执行完成

        System.out.println(test01.i);
    }
}
