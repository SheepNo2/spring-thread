package com.yang;

/**
 * @author yangxiangyang 2019年7月12日 描述：2种方式
 */
public class T02_CheckThreadInterface {
	public static void main(String[] args) {
		Thread thread = new Thread(new MyThreadInter()) ;
		thread.start();
		System.out.println("main " + Thread.currentThread().getName());
	}
}

class MyThreadInter implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("MyThreadInter " + Thread.currentThread().getName());
	}
}
