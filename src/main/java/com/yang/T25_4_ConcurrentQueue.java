package com.yang;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T25_4_ConcurrentQueue {

	public static void main(String[] args) {
		Queue<String> strings = new ConcurrentLinkedQueue<String>();
		
		for (int i = 0; i < 10; i++) {
//			Inserts the specified element at the tail of this queue
			strings.offer("a" + i); //相当于add， 放进队列
		}
		
		System.out.println(strings);
		
		System.out.println(strings.size());
//		Retrieves and removes the head of this queue
		System.out.println(strings.poll()); //取出并移除掉
		System.out.println(strings.size());
//		Retrieves, but does not remove, the head of this queue,
		System.out.println(strings.peek()); //取出，不会移除。相当于get(0)
		System.out.println(strings.size());
	}
}
