package com.china.test.thread;

import java.util.concurrent.CountDownLatch;

public class MyThread2 extends Thread{
    private CountDownLatch countDownLatch;
    public MyThread2(CountDownLatch latch) {
        countDownLatch = latch;
    }

    @Override
        public void run() {
            System.out.println("线程执行了：" + Thread.currentThread().getName());
            countDownLatch.countDown();
            int i = 1 / 10;
        }
    }