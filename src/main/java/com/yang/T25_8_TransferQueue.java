package com.yang;


import java.util.concurrent.LinkedTransferQueue;

public class T25_8_TransferQueue {
	//JDK1.7才添加的阻塞队列，基于链表实现的FIFO无界阻塞队列
//		是ConcurrentLinkedQueue（循环CAS+volatile 实现的wait-free并发算法）、
//		SynchronousQueue（公平模式下转交元素）、
//		LinkedBlockingQueue（阻塞Queue的基本方法）的超集。
//		而且LinkedTransferQueue更好用，因为它不仅仅综合了这几个类的功能，同时也提供了更高效的实现
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strings = new LinkedTransferQueue<String>();
		
		/*new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();*/
		
		//strings.transfer("aaa");
		// 1-消费者先启动，生产者启动，先去找有没有消费者，有则消费，无则添加到队列
		// 2-生产者先启动，没有消费者则阻塞
		strings.put("aaa");//使用put add offer均不会阻塞
		
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
