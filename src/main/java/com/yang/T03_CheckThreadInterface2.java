package com.yang;

/**
 * @author yangxiangyang 2019年7月12日 描述：2种方式
 */
public class T03_CheckThreadInterface2 {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Runnable " + Thread.currentThread().getName());
			}
		});
		thread.start();
		System.out.println("main " + Thread.currentThread().getName());
	}
}
