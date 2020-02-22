package com.china.test.thread;

public class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("线程执行了：" + Thread.currentThread().getName());
        }
    }