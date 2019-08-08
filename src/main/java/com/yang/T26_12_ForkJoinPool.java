package com.yang;


import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork就是把一个大任务切分为若干子任务并行的执行，
 * Join就是合并这些子任务的执行结果，最后得到这个大任务的结果
 *
 * 其中ForkJoinTask代表一个可以并行、合并的任务。
 * ForkJoinTask是一个抽象类，
 * 它有两个抽象子类：RecursiveAction和RecursiveTask。
 *
 * RecursiveTask代表有返回值的任务
 * RecursiveAction代表没有返回值的任务。
 */
public class T26_12_ForkJoinPool {

	//计算100w个随机数的数组集合的和

	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();
	
	static {
		long start = System.currentTimeMillis();
		for(int i=0; i<nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		System.out.println("stream:"+Arrays.stream(nums).sum()); //stream api
		System.out.println("init:"+(System.currentTimeMillis() - start));
	}
	
	/*
	static class AddTask extends RecursiveAction { 
		
		int start, end;
		
		AddTask(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		protected void compute() {
			
			if(end-start <= MAX_NUM) {
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				System.out.println("from:" + start + " to:" + end + " = " + sum);
			} else {
			
				int middle = start + (end-start)/2;
				
				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();
			}
			
			
		}
		
	}
	*/
	
	static class AddTask extends RecursiveTask<Long> { 
		
		int start, end;
		
		AddTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		protected Long compute() {
			
			if(end-start <= MAX_NUM) {//50000
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				return sum;
			} 
			
			int middle = start + (end-start)/2;
			
			AddTask subTask1 = new AddTask(start, middle);
			AddTask subTask2 = new AddTask(middle, end);
			subTask1.fork();
			subTask2.fork();
			
			return subTask1.join() + subTask2.join();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		fjp.execute(task);
		long result = task.join();
		System.out.println("join:"+result);
		System.out.println("join:"+(System.currentTimeMillis() - start));
		
		//System.in.read();
		
	}
}
