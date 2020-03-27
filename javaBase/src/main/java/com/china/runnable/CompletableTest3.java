package com.china.runnable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * {@link CompletableFuture#supplyAsync}
 * {@link CompletableFuture#thenApply}
 */
public class CompletableTest3 {
     static ExecutorService executor = Executors.newFixedThreadPool(3, r -> {
        Thread t = new Thread(r);
        t.setDaemon(false);
        return t;
    });

    public static void main(String[] args) {

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<Integer> collect = intList.parallelStream()
                .map(CompletableTest3::completableMulti)
                .map(i -> i.thenApply(CompletableTest3::completableThen))
                .map(CompletableFuture::join)
                .collect(toList());

        System.out.println("result : " + collect);
        executor.shutdown();
    }

    private static CompletableFuture<Integer> completableMulti(Integer i) {
        System.out.println(i + " - " + Thread.currentThread().getName() + " : " + i * 10);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.supplyAsync(() -> i * 10, executor);
    }

    private static Integer completableThen(Integer i) {
        System.out.println(i + " - " + Thread.currentThread().getName() + " : " + (i + 5));
        return i + 5;
    }
}
