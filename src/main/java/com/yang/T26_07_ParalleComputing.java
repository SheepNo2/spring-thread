package com.yang;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池的概念
 *
 */
public class T26_07_ParalleComputing {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//单线程
		long start = System.currentTimeMillis();
		List<Integer> results = getPrime(1, 200000);
//		System.out.println(results.size());
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		//对比上面的，4线程，对比时间
		final int cupCoreNum = 4;
		
		ExecutorService service = Executors.newFixedThreadPool(cupCoreNum);
		
		MyTask myTask1 = new MyTask(1, 80000);
		MyTask myTask2 = new MyTask(80001, 130000);
		MyTask myTask3 = new MyTask(130001, 170000);
		MyTask myTask4 = new MyTask(170001, 200000);
		
		Future<List<Integer>> f1 = service.submit(myTask1);
		Future<List<Integer>> f2 = service.submit(myTask2);
		Future<List<Integer>> f3 = service.submit(myTask3);
		Future<List<Integer>> f4 = service.submit(myTask4);

		start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		System.out.println(end - start);
		
	}
	
	static class MyTask implements Callable<List<Integer>> {

		int startPos, endPos;
		
		public MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}
		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos, endPos);
			return r;
		}
	}

	/**
	 * 开方，然后除2....，返回true是质数，false为素数
	 * @param num
	 * @return
	 */
	static boolean isPrime(int num){
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 计算从start到end之前都有多少个质数
	 * @param start
	 * @param end
	 * @return
	 */
	static List<Integer> getPrime(int start, int end){
		List<Integer> results = new ArrayList<Integer>();
		for (int i = start; i <= end; i++) {
			if (isPrime(i)) {
				results.add(i);
			}
		}
		return results;
	}
	
}
