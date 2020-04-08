package com.china.designPattern.singleton;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SingletonTest3 {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Resource2> queue = new ArrayBlockingQueue<>(2000);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(50,50,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1500), new ThreadPoolExecutor.AbortPolicy());
        IntStream.rangeClosed(1, 1000).forEach(i -> threadPool.submit(() -> {
            Resource2 temp = SingletonInnerClass.getInstance();
            queue.add(temp);
            System.out.println(Thread.currentThread().getName() + " - " + temp);
        }));

        while (threadPool.getCompletedTaskCount() != 1000) {
            Thread.sleep(1);
        }
        threadPool.shutdown();

        System.out.println("queueSize：" + queue.size());
        boolean allMatch = queue.stream().allMatch(demo -> demo == queue.poll());
        System.out.println("allMatch: " + allMatch);
    }
}