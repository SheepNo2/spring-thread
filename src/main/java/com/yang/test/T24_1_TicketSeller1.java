package com.yang.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 有N张火车票，每张票都有一个编号 同时有10个窗口对外售票 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题 重复销售？超量销售？
 */
public class T24_1_TicketSeller1 {

	private static List<String> tickets = new ArrayList<>();

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			tickets.add("票编号：" + i);
		}
		System.out.println(tickets);
		
		System.out.println(tickets.remove(0));
		System.out.println(tickets);
		

	}
}
