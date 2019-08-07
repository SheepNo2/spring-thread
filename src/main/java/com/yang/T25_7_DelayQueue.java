package com.yang;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T25_7_DelayQueue {

	/**
	 * BlockingQueue即阻塞队列，它是基于ReentrantLock
	 * 在Java中，BlockingQueue是一个接口，
	 * 它的实现类有ArrayBlockingQueue、DelayQueue、 LinkedBlockingDeque、
	 * LinkedBlockingQueue、PriorityBlockingQueue、SynchronousQueue等，
	 * 它们的区别主要体现在存储结构上或对元素操作上的不同，但是对于take与put操作的原理，却是类似的
	 * 
	 * 
	 * 
	 * DelayQueue
	 * 延时获取
	 * 是一个无界的BlockingQueue
	 * 用于放置实现了Delayed接口的对象，其中的对象只有时间到期时才能取走。
	 * 这种队列是有序的，即队头对象的延迟到期时间最长，。
	 * 注意：不能将null元素放置到这种队列中。
	 */
	private static BlockingQueue<MyTask> tasks = new DelayQueue<>();
	private static Random r = new Random();
	
	static class MyTask implements Delayed{

		long runningTime;
		
		public MyTask(long rt) {
			this.runningTime = rt;
		}
		
		@Override
		public int compareTo(Delayed o) {
			if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MICROSECONDS)) {
				return -1;
			}else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			}else {
				return 0;
			}
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}
		
		@Override
		public String toString() {
			return "" + runningTime;
		}
		
		public static void main(String[] args) throws InterruptedException {
			long now = System.currentTimeMillis();
			MyTask t1 = new MyTask(now + 1000);
			MyTask t2 = new MyTask(now + 2000);
			MyTask t3 = new MyTask(now + 1500);
			MyTask t4 = new MyTask(now + 2500);
			MyTask t5 = new MyTask(now + 500);
			
			tasks.put(t1);
			tasks.put(t2);
			tasks.put(t3);
			tasks.put(t4);
			tasks.put(t5);
			
			System.out.println(tasks);
			
			for (int i = 0; i < 5; i++) {
				System.out.println(tasks.take());
			}
		}
	}
}
