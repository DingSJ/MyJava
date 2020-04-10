package com.china.runnable;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompletableTest {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(false);
                return t;
            }
        });

        ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("程序执行完毕....");
                atomicBoolean.set(true);
                return "Susscess";
            } catch (InterruptedException e) {
                atomicBoolean.set(true);
                e.printStackTrace();
                return "False";
            }
        },executorService).whenComplete((val, thr) -> {
            System.out.println("获取执行结果 val: " + val);
            System.out.println("Throw Exception : " + thr.getMessage());
        });

        System.out.println("StaticMain execute ... ");

        // 1
//        Thread.currentThread().join();

        // 2
//        while (!future.isDone()) {
//            TimeUnit.SECONDS.sleep(1);
//        }

        // 3
//        while (!atomicBoolean.get()) {
//            TimeUnit.SECONDS.sleep(1);
//        }
    }
}
