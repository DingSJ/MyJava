package com.china.test.thread.threadInterrupt2;

import java.util.concurrent.TimeUnit;

public class MyRunnable3 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("线程333开始执行");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("中断异常333");
        }
        System.out.println("线程333执行完毕+++++++++++++++++");
    }
}
