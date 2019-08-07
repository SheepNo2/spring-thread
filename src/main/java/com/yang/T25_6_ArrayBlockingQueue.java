package com.yang;


import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T25_6_ArrayBlockingQueue {
//	有界，在创建时，必须要给它指定一个队列的大小
	private static BlockingQueue<String> strings = new ArrayBlockingQueue<String>(10);
	
	private static Random r = new Random();
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			strings.put("a" + i);
		}
		
//		strings.put("aaaa"); //满了就会等待，程序阻塞
//		System.out.println(strings.add("aaaa"));//满了添加失败 ，异常Queue full
//		System.out.println(strings.offer("aaaa"));//满了添加失败，返回false
		System.out.println(strings.offer("aaaa", 1, TimeUnit.SECONDS));//满了就等待1s，还满则返回false
		
		System.out.println(strings);
	}
}
