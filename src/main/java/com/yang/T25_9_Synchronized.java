package com.yang;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * https://blog.csdn.net/qq_26881739/article/details/80983495
 *
 * SynchronousQueue是一个双栈双队列算法，无空间的队列或栈，
 *
 * 没有容量，是无缓冲等待队列，
 * 是一个不存储元素的阻塞队列，会直接将任务交给消费者，必须等队列中的添加元素被消费后才能继续添加新的元素。
 *
 * 任何一个对SynchronousQueue写需要等到一个对SynchronousQueue的读操作，反之亦然。
 * 一个读操作需要等待一个写操作，相当于是交换通道，提供者和消费者是需要组队完成工作，缺少一个将会阻塞线程，知道等到配对为止。
 *
 * SynchronousQueue是一个队列和栈算法实现，
 * 在SynchronousQueue中双队列FIFO提供公平模式，而双栈LIFO提供的则是非公平模式。
 *
 * 对于SynchronousQueue来说，他的put方法和take方法都被抽象成统一方法来进行操作，
 * 通过抽象出内部类Transferer，来实现不同的操作。
 */
public class T25_9_Synchronized {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strings = new SynchronousQueue<String>();//没有容量
		
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
//		strings.put("aaa"); //阻塞等待消费者消费
		strings.add("aaa");//异常，满了
		System.out.println(strings.size());
	}
}
