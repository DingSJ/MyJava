package com.china.test.thread.threadGroup;

import com.alibaba.fastjson.JSON;

import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程组
 *
 * */
public class ThreadGroupTest1 {

    public static void main(String[] args) {
        ThreadGroup threadGroup1 = new ThreadGroup("MY-Group-1");
        ThreadGroup threadGroup2 = new ThreadGroup("MY-Group-2");

        Thread t1 = new Thread(threadGroup1, "Thread-1"){
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 3; i++) {
                        System.out.println(currentThread() + " - " + i);
                        TimeUnit.SECONDS.sleep(1);
                    }
                    System.out.println(currentThread() + " - " + "执行完毕============");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        Thread t2 = new Thread(threadGroup2, "Thread-2"){
            @Override
            public void run() {
                Thread t = new Thread(threadGroup2,new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            Thread[] threads = new Thread[threadGroup1.activeCount()];

                            // 遍历线程，将线程信息放到 list 中
//                            threadGroup1.enumerate(threads);
//                            System.out.println(JSON.toJSONString(threads));


                            for (int i = 0; i < 5; i++) {
                                System.out.println(currentThread() + " - " + i);
                                TimeUnit.SECONDS.sleep(1);
                            }

                            System.out.println(currentThread() + " - " + "执行完毕============");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.setDaemon(true);
                t.setName("My-Task-2");
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    System.out.println(currentThread() + "， Task Interrupted By Main Thread");
                    e.printStackTrace();
                }
            }
        };
        t2.start();

        Thread t3 = new Thread(threadGroup2, "Thread-3"){
            @Override
            public void run() {
                Thread t = new Thread(threadGroup2,new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 0; i < 15; i++) {
                                System.out.println(currentThread() + " - " + i);
                                TimeUnit.SECONDS.sleep(1);
                            }

                            System.out.println(currentThread() + " - " + "执行完毕============");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.setDaemon(true);
                t.setName("My-Task-3");
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    System.out.println(currentThread() + "， Task Interrupted By Main Thread");
//                    e.printStackTrace();
                }
            }
        };
        t3.start();

        try {
            TimeUnit.SECONDS.sleep(6);
//            t2.interrupt();
            threadGroup2.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Main 捕获到异常");
        }

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("======================");
        System.out.println(Thread.currentThread());



//        // 需要显示关闭
//        if (!threadGroup1.isDestroyed()) {
//            threadGroup1.destroy();
//        }
//        if (!threadGroup2.isDestroyed()) {
//            threadGroup2.destroy();
//        }


        System.out.println(threadGroup1.isDestroyed());
        System.out.println(threadGroup2.isDestroyed());
    }
}
