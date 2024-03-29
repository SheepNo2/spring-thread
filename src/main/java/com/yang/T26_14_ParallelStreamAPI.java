package com.yang;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 判断100w个数是否是质数
 * 当前方法未求和
 */
public class T26_14_ParallelStreamAPI {
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for(int i=0; i<10000; i++) nums.add(1000000 + r.nextInt(1000000));//把100w个随机数求和
		
		//System.out.println(nums);
		
		long start = System.currentTimeMillis();
		nums.forEach(v->isPrime(v));
		long end = System.currentTimeMillis();
		System.out.println("common:"+(end - start));
		
		//使用parallel stream api
		
		start = System.currentTimeMillis();
		nums.parallelStream().forEach(T26_14_ParallelStreamAPI::isPrime);//parallelStream并行操作
		end = System.currentTimeMillis();
		
		System.out.println("parallelStream:"+(end - start));
	}

	/**
	 * true质数，false素数
	 * @param num
	 * @return
	 */
	static boolean isPrime(int num) {
		for(int i=2; i<= Math.sqrt(num); i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}
