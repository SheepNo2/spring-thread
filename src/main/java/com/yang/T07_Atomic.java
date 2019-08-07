package com.yang;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 证明AtomicXX类比synchronized更高效
 */
public class T07_Atomic {

	int count = 0;

    AtomicInteger count2 = new AtomicInteger(0);

    synchronized void m() {
        count++;
    }

    void m2() {
        count2.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
    	T07_Atomic t = new T07_Atomic();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(t::m);
            thread.start();
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("m 所 耗用时间 " + (end - start));

        long start2 = new Date().getTime();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(t::m2);
            thread.start();
            thread.join();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("m2 所耗用时间 " + (end2 - start2));
    }
}

