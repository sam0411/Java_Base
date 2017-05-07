package com.fil.ap.base.thread;

public class VolatileReorder {
	
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
	public static volatile VolatileReorder singleton;

    /**
     * 构造函数私有，禁止外部实例化
     */
    private VolatileReorder() {};

    public static VolatileReorder getInstance() {
    	
        if (singleton == null) {
        	
            synchronized (singleton) {
            	
                if (singleton == null) {
                	
                    singleton = new VolatileReorder();
                }
            }
        }
        
        return singleton;
    }
}
