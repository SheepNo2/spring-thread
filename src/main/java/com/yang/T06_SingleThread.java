package com.yang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangxiangyang
 * 2019年7月16日 描述：
 * 启动一个线程，保证顺序
 */
public class T06_SingleThread {
	public static void main(String[] args) {
		ExecutorService service=Executors.newSingleThreadExecutor();
		for (int i = 0 ; i<5 ;i++) {
			final int j=i;
			service.execute(()->{
				System.out.println( j+" "+Thread.currentThread().getName() );
			});
		}
	}
}
