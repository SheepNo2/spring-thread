package com.yang;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，是一个变量在多个线程间可见
 *
 */
public class T12_Thread {
	/*volatile*/ boolean running = true;

	public void test() {
		System.out.println("start....");
		while (running) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("ing....");
		}
		System.out.println("end....");
	}

	/**
	 * @author yangxiangyang 2019年7月17日 描述：
	 */
	public static void StartThread1() {// 线程的第一种写法
		T12_Thread t = new T12_Thread();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				t.test();
			}
		};
		new Thread(runnable, "name1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.running = false;
	}

	public static void StartThread2() {// 线程的第二种写法
		T12_Thread t = new T12_Thread();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.test();
			}
		}, "name2").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.running = false;
	}

	public static void StartThread3() {// 线程的第三种写法
		T12_Thread t = new T12_Thread();
		new Thread(t::test, "name3").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.running = false;
	}

	public static void main(String[] args) {
		StartThread3();
	}
}
