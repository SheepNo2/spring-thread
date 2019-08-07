package com.yang;

/**
 * @author yangxiangyang 2019年7月12日 描述：4种方式 继承
 */
public class T01_CheckThread {
	public static void main(String[] args) {
		Thread a = new MyThread();
		a.start();
		System.out.println("main " + Thread.currentThread().getName());
	}
}
class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println("run "+Thread.currentThread().getName());
	}
}
