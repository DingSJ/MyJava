package com.china.designPattern.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.IntStream;

public class SingletonTest2 {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<SingletonLazy> queue = new ArrayBlockingQueue<>(2000);
        IntStream.rangeClosed(0, 1000).forEach(i->{
            new Thread(()->{
                SingletonLazy temp = SingletonLazy.getInstance();
                queue.add(temp);
                System.out.println(Thread.currentThread().getName() + " - " + temp);
            },"My-Thread-" + i).start();
        });

        // 等待任务执行完
        Thread.sleep(3);
        SingletonLazy temp = SingletonLazy.getInstance();
        boolean allMatch = queue.stream().allMatch(singleton -> singleton == temp);
        System.out.println("allMatch: " + allMatch);
    }
}
