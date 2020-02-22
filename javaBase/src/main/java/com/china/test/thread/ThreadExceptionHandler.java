package com.china.test.thread;

public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName() + "  throws an exception");
        e.printStackTrace();
    }
}