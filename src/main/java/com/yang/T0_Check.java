package com.yang;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class T0_Check {
	public static void main(String[] args) {
		Executors.newFixedThreadPool(0);
		
		Executors.newCachedThreadPool();
		Executors.newSingleThreadExecutor();
		Executors.newScheduledThreadPool(0);
		Executors.newWorkStealingPool();
		ForkJoinPool jongo = new ForkJoinPool();
		
		
		
	}

}
