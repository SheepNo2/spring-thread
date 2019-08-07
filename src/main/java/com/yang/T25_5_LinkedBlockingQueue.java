package com.yang;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class T25_5_LinkedBlockingQueue {

	private static BlockingQueue<String> strings = new LinkedBlockingQueue<String>();
	private static Random r = new Random();

	public static void main(String[] args) {
		//一个线程添加100个
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					// 如果满了，就会等待
					strings.put("a" + i);
					System.out.println("--------add------------- "+i);
					TimeUnit.SECONDS.sleep(r.nextInt(10));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "p1").start();
		
		//5个线程取出
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (;;) {
					try {
						// 如果空了，就会等待
						System.out.println(Thread.currentThread().getName() + "take -" + strings.take());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, "c" + i).start();
		}
	}
}
