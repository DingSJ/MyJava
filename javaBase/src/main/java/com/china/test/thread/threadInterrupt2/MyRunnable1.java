package com.china.test.thread.threadInterrupt2;

import java.util.concurrent.TimeUnit;

public class MyRunnable1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("线程1111111111111执行任务 : " + i);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("中断异常1111111111");
            }
        }
        System.out.println("线程1111111111111执行完毕+++++++++++++++++");
    }
}
