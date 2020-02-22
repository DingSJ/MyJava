package com.china.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadStep3 extends Thread {
    private CountDownLatch countDownLatch;
    private AtomicInteger count;
    public ThreadStep3(CountDownLatch countDownLatch, AtomicInteger count) {
        this.countDownLatch = countDownLatch;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            System.out.println("步骤三 Start .... ");

            for (int i = 0; i < 10; i++) {
                System.out.println("步骤三： " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                int err = 1/0;
//                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count.getAndIncrement();
            System.out.println("步骤三 End .... ");
        }finally {
            countDownLatch.countDown();
        }

    }
}
