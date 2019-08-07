package com.yang;

import java.util.concurrent.TimeUnit;

public class T12_2 {
/*volatile*/ boolean running = true;
	
	public void test(){
		System.out.println("test start.......");
		while (running) {
			/*try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		System.out.println("test end........");
	}
	
	public static void main(String[] args) {
		T12_2 T12_2 = new T12_2();
		new Thread(T12_2 :: test, "t1").start(); //JDK1.8新特性
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		T12_2.running = false;
	}
}
