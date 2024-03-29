package com.yang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题： 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 
 * 给lists添加volatile之后，t2能够接到通知，但是,t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 * 
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁 需要注意的是运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * 
 * 阅读下面的程序，并分析输出结果 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才收到通知而退出
 * 
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 
 * @author Jcon
 *
 */
public class T19_4 {

	// 添加volatile，使t2能够得到通知
	volatile List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		T19_4 c = new T19_4();
		Object lock = new Object();

		new Thread(() -> { // 线程一
			synchronized (lock) {
				System.out.println("t2启动");
				if (c.size() != 5) {
					try {
						lock.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2结束");
				lock.notify();
			}
		}, " t2").start();

		new Thread(() -> { // 线程二
			System.out.println("t1启动");
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					c.add(new Object());
					System.out.println("add " + i);

					if (c.size() == 5) {
						lock.notify();

						// 释放锁，让t2得以执行
						try {
							lock.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();
	}
}
