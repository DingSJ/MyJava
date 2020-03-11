package com.china.test.future;

import java.util.concurrent.*;

public class FutureInterruptTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> task = executorService.submit(()->{
            System.out.println("Start...");
            Thread.sleep(5_000);
            System.out.println("End...");
            return 1;
        });

        // 打算 Main 线程
        Thread currThread = Thread.currentThread();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Interrupt..");
                currThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        try {
            Integer result = task.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
