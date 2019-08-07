package com.yang.test;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T25_7_DelayQueue {

	private static BlockingQueue<MyTask> tasks = new DelayQueue<>();
	private static Random r = new Random();
	
	static class MyTask implements Delayed{

		@Override
		public int compareTo(Delayed o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
	}
}
