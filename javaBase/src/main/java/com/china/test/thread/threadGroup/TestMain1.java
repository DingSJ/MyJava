package com.china.test.thread.threadGroup;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 线程组的使用
 * 多线程任务中断
 * */
public class TestMain1 {
    public static void main(String[] args) {

        ThreadGroup threadGroup = new ThreadGroup("我的任务执行线程组");
        AtomicBoolean flag = new AtomicBoolean(true);

        Thread t1 = new Thread(threadGroup, new MyThread1(flag), "线程一");
        Thread t2 = new Thread(threadGroup, new MyThread2(flag), "线程二");
        Thread t3 = new Thread(threadGroup, new MyThread3(flag), "线程三");

        t1.setUncaughtExceptionHandler(new MyThreadException(flag));
        t2.setUncaughtExceptionHandler(new MyThreadException(flag));
        t3.setUncaughtExceptionHandler(new MyThreadException(flag));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
        }

        System.out.println("主线程执行结束");
    }
}
