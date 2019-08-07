package com.yang;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;import java.util.concurrent.TimeUnit;


/**
 * 线程池的概念
 * @author Jcon
 *
 */
public class T26_05_ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5); // 固定数量的线程池
		for (int i = 0; i < 6; i++) {
			service.execute(()->{
				try {
					TimeUnit.MICROSECONDS.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(service);
		
		service.shutdown();
		System.out.println(service.isTerminated());//所有的线程结束返回ture，shutdown或者shutdownNow首先执行才可以
		System.out.println(service.isShutdown());//executor结束返回true
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}
}
