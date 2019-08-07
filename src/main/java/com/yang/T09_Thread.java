package com.yang;

import java.util.concurrent.TimeUnit;
/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候
 * 仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 * @author Jcon
 *
 */
public class T09_Thread {

	public synchronized void test1() {
		System.out.println("test1 "+Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test2();
	}
	
	
	public synchronized void test2() {
		System.out.println("test2 "+Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		T09_Thread t=new T09_Thread();
		t.test1();
	}
}
