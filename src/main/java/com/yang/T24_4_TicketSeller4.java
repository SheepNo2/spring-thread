package com.yang;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号 同时有10个窗口对外售票 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？ 重复销售？超量销售？
 * 
 * 使用Vector或者Collections.synchronizedXXX 分析一下，这样能解决问题吗？
 * 
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步 就像这个程序，判断size和进行remove必须是一整个的原子操作
 * 
 * 使用ConcurrentQueue提高并发性
 * 
 * @author Jcon
 *
 */
public class T24_4_TicketSeller4 {

	private static Queue<String> tickets = new ConcurrentLinkedQueue<String>();

	static {
		for (int i = 0; i < 10000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
//					Retrieves and removes the head of this queue, or returns null if this queue is empty.
					String s = tickets.poll();
					if (s == null) {
						System.out.println("用时"+(System.currentTimeMillis()-startTime)+"ms");
						break;
					}

					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("销售了>>>>" + s);
				}
			}).start();
		}

	}
}
