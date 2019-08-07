package com.yang;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class T04_CheckCallable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
		Thread thread = new Thread(futureTask);
		thread.start();
		System.out.println("打印：" + futureTask.get());

	}
}

class MyCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return "hello";
	}
}
