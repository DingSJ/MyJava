package com.china.test.thread;

/**
 * 线程启动
 * */
public class ThreadTestMain {
    public static void main(String[] args) {
        Thread thread = new MyThread(1);
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("当前执行：" + i);
        }

        System.out.println("程序结束");
    }
}
