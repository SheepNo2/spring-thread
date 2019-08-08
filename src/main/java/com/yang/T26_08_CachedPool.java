package com.yang;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 	创建一个可缓存线程池，
 * 	如果线程池长度超过处理需要，可灵活回收空闲线程，
 * 	若无可回收，则新建线程。
 *
 * 	线程池为无限大，
 * 	当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
 */
public class T26_08_CachedPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println("init:"+service);
		
		for (int i = 0; i < 2; i++) {
			service.execute(()->{
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println("statrt:"+service);
		
		TimeUnit.SECONDS.sleep(10);
		
		System.out.println("end:"+service);
	}
}
