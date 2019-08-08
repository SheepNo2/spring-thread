package com.yang;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 	创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，
 * 	保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 */
public class T26_09_SingleThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 5; i++) {
			final int j = i;
			service.execute(()->{
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
	}
}
