package com.china.runnable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * {@link CompletableFuture#supplyAsync(Supplier)}
 * {@link CompletableFuture#thenApply(Function)}
 * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
 * {@link CompletableFuture#thenAccept(Consumer)}
 */
public class CompletableTest4 {
     static ExecutorService executor = Executors.newFixedThreadPool(5, r -> {
        Thread t = new Thread(r);
        t.setDaemon(false);
        return t;
    });

    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> 1)
        .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (v1,v2) -> {
            System.out.println("v1 : " + v1);
            System.out.println("v2 : " + v2);
            return v1 + v2;
        }).thenAccept(System.out::println);

        executor.shutdown();
    }
}
