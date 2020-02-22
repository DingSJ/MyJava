package com.china.test.thread.threadGroup;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThread1 extends Thread {
    private AtomicBoolean flag;
    public MyThread1(AtomicBoolean flag) {
        this.flag = flag;
    }

    public void setFlag(AtomicBoolean flag) {
        this.flag = flag;
    }

    public AtomicBoolean getFlag() {
        return flag;
    }

    @Override
    public void run() {
        while (flag.get()) {
            System.out.println(".... " + Thread.currentThread().getName() + ": interrupted： " + Thread.currentThread().isInterrupted());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("系统中断 : " + Thread.currentThread().getName());
            }
        }
    }
}
