package com.china.runnable;

import java.util.Random;
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
 * acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
 *       当任意一个CompletableFuture完成的时候，action这个消费者就会被执行。
 *
 *
 * acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
 *       当任意一个CompletableFuture完成的时候，action这个消费者就会被执行。使用ForkJoinPool
 *
 *
 * acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor)
 *       当任意一个CompletableFuture完成的时候，action这个消费者就会被执行。使用指定的线程池
 *
 *
 *       // Consumer.get - Function.apply
 *       applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn) {
 *       acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
 */
public class CompletableTest6 {
    static ExecutorService executor = Executors.newFixedThreadPool(5, r -> {
        Thread t = new Thread(r);
        t.setDaemon(false);
        return t;
    });

    public static void main(String[] args) {
        Random random = new Random();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from future2";
        });

        CompletableFuture<Void> future = future1.acceptEither(future2, str -> System.out.println("The future is " + str));

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
