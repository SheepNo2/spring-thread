package com.yang;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况 比如，在一个web
 * app处理过程中，多个servlet线程共同访问通一个资源，这是如果异常处理不合适
 * 在第一个线程中抛出异常，其他线程就会进入同步代码去，有可能访问到异常产生是的数据 因此要非常小心的处理同步业务逻辑中的异常
 * 
 * @author Jcon
 *
 */
public class T11_Thread {

	synchronized void test() {
		int count=0;
		System.out.println(Thread.currentThread().getName()+" start");
		while (true) {
			count++;
			System.out.println(Thread.currentThread().getName()+" count="+count);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(count ==5) {
				int i=1/0;//遇到异常，锁自动释放，不想释放，就catch异常处理
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		T11_Thread t=new T11_Thread();
		
		Runnable r=new Runnable() {
			@Override
			public void run() {
				t.test();
			}
		};
		
		new Thread(r ,"name1").start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(r,"name2").start();
		
	}


}
