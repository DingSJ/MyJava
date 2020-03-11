package com.china.test.thread;

public class MyThread extends Thread{
    private int i;
    public MyThread(int i) {
        this.i = i;
    }

    @Override
        public void run() {
            System.out.println("线程执行了：" + i);
        }
    }