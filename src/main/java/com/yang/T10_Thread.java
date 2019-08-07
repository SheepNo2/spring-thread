package com.yang;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁， 再次申请的时候仍然会得到该对象的锁，也就是说synchronize获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 */
public class T10_Thread {

	synchronized void test() {
		System.out.println("test start........");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test end........");
	}

	public static void main(String[] args) {
		new Demo100().test();
	}
}

class Demo100 extends T10_Thread {
	@Override
	synchronized void test() {
		System.out.println("child test start.......");
		super.test();
		System.out.println("child test end.......");
	}
}