package com.china.test.thread;

import java.util.concurrent.CyclicBarrier;

public class MyThread3 extends Thread{
    private CyclicBarrier cyclicBarrier;
    public MyThread3(CyclicBarrier cyclicBarrier) {
        cyclicBarrier = cyclicBarrier;
    }

    @Override
        public void run() {
            System.out.println("线程执行了：" + Thread.currentThread().getName());
            cyclicBarrier.getParties();
//            int i = 1 / 10;
        }
    }