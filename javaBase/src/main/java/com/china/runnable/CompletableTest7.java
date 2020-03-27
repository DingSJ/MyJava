package com.china.runnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * {@link CompletableFuture#allOf(CompletableFuture[])}  等到所有结果执行完毕再执行
 * {@link CompletableFuture#anyOf(CompletableFuture[])}  只要一个 CompletableFuture 执行完毕 就 执行
 */
public class CompletableTest7 {
    static ExecutorService executor = Executors.newFixedThreadPool(5, r -> {
        Thread t = new Thread(r);
        t.setDaemon(false);
        return t;
    });

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<CompletableFuture<Integer>> collect = intList.stream()
                .map(i -> CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " - " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i * 10;
                }))
                .collect(Collectors.toList());
        
//        iteratorCompleable(intList);
        
        allOfCompleable(collect, intList);

//        anyOfCompleable(collect, intList);

        executor.shutdown();
    }

    private static void printCollect(List<CompletableFuture<Integer>> collect) {
        collect.stream().forEach(i -> {
            try {
                System.out.println("get : " + i.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void anyOfCompleable(List<CompletableFuture<Integer>> collect, List<Integer> intList) {

        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()])).thenRun(()-> System.out.println("Done ..."));
        printCollect(collect);
    }

    private static void allOfCompleable(List<CompletableFuture<Integer>> collect, List<Integer> intList) {
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()])).thenRun(()-> System.out.println("Done ..."));
        printCollect(collect);
    }

    private static void iteratorCompleable(List<Integer> intList) {
        List<Integer> collect = intList.stream()
                .map(i -> CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " - " + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i * 10;
                })).map(CompletableFuture::join).collect(Collectors.toList());

        System.out.println(collect);
    }
}
