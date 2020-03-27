package com.china.runnable;

import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@link CompletableFuture#supplyAsync(Supplier)}
 * {@link CompletableFuture#thenApply(Function)}
 * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
 * {@link CompletableFuture#thenAccept(Consumer)}
 * {@link CompletableFuture#applyToEither(CompletionStage, Function)}
 *
 *
 *
 *
 */
public class CompletableTest5 {
     static ExecutorService executor = Executors.newFixedThreadPool(5, r -> {
        Thread t = new Thread(r);
        t.setDaemon(false);
        return t;
    });

    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " i am apply 1");
            return 1;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " i am apply 2");
            return 2;
        }), v -> v * 10).thenAccept(System.out::println);

        executor.shutdown();
    }
}
