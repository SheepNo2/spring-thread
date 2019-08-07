package com.yang;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 * @author Jcon
 *
 */
public class T25_2_CopyOnWriteList {
	public static void main(String[] args) {
		List<String> lists = 
				//这个会出并发问题
//				new ArrayList<String>(); 
				//time=10124//size=100000  43
// 				new Vector<String>(); 
				//time=10218//size=100000  4223
//				多线程环境下，写时效率低，读时效率高
				new CopyOnWriteArrayList<String>();
		Random r = new Random();
		Thread[] threads = new Thread[100];
		/*for (int i = 0; i < threads.length; i++) {
			Runnable task = new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						lists.add("a" + r.nextInt(10000));
					}
				}
			};
			threads[i] = new Thread(task);
		}*/
		/*for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						lists.add("a" + r.nextInt(10000));
					}
				}
			});
		}*/
		//启动100个线程
		//每个线程往list添加1000个对象
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					lists.add("a" + r.nextInt(10000));
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		runAndComputeTime(threads);
		System.out.println("size="+lists.size());
	}

	private static void runAndComputeTime(Thread[] threads) {
		long start = System.currentTimeMillis();
		Arrays.asList(threads).forEach(t->t.start());
		Arrays.asList(threads).forEach(t->{
			try {
				t.join(); //join等线程执行完之后再往下执行 ，多个线程执行完毕后执行main线程需要join
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		long end = System.currentTimeMillis();
		System.out.println("time="+(end - start));
	}
}

