package com.china.runnable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * {@link CompletableFuture#supplyAsync}
 * {@link CompletableFuture#thenApply}
 */
public class CompletableTest2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("程序执行完毕....");
                return "Success";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "False";
            }
        }, executor).thenApply(value -> {
            if ("Success".equals(value)) {
                return value + " : ThenApp";
            } else {
                return value + "T ....";
            }
        }).whenComplete((val, thr) -> {
            System.out.println("获取执行结果 val: " + val);
            System.out.println("Throw Exception : " + thr.getMessage());
        });

        executor.shutdown();
    }
}
