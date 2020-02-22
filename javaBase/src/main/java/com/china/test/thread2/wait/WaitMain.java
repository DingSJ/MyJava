package com.china.test.thread2.wait;

import java.security.acl.LastOwnerException;
import java.util.Optional;

public class WaitMain {

    private static Object OBJECT = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (OBJECT) {
                        System.out.println("程序阻塞.....");
                        try {
                            OBJECT.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Optional.of("CCCCC").ifPresent(System.out::println);
                        System.out.println("处理完毕");
                    }
                }
            });

            t.start();
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        try {
            Thread.sleep(1_0L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (OBJECT) {
            OBJECT.notifyAll();
        }
        System.out.println("主程序 OVER ");
    }
}
