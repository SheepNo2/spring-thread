package com.yang;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 创建持有足够线程的线程池来支持给定的并行级别，
 * 并通过使用多个队列，减少竞争，它需要穿一个并行级别的参数，
 * 如果不传，则被设定为默认的CPU数量
 *
 * 由于能够合理的使用CPU进行对任务操作（并行操作），所以适合使用在很耗时的任务中
 * 是线程池类ForkJoinPool的扩展
 */
public class T26_11_WorkStealingPool {

	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());//查询电脑是几核的，打印出4
		
		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		
		//由于产生的是精灵线程(守护线程、后台线程)，主线程不阻塞的话，看不到输出
		System.in.read();
	}
	
	static class R implements Runnable{

		int time;
		
		public R (int t){
			this.time = t;
		}
		
		@Override
		public void run() {
			
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(time + " " + Thread.currentThread().getName());
		}
		
	}
}
