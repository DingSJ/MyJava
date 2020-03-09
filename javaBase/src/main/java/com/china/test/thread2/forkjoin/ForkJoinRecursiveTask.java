package com.china.test.thread2.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * 任务分而治之
 * */
public class ForkJoinRecursiveTask {

    private static final int MAX_THRESHOLD = 6;

    public static void main(String[] args) {
        ForkJoinPool joinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = joinPool.submit(new MyForkJoinRecursiveTask(0, 1000));

        try {
            int value = future.get();
            System.out.println("Value: " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyForkJoinRecursiveTask extends RecursiveTask<Integer>{

        private int start;
        private int end;

        MyForkJoinRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= MAX_THRESHOLD) {
                return IntStream.rangeClosed(start,end).sum();
            }else{
                int middle = (end + start) / 2;
                System.out.println("middle: " + middle);

                MyForkJoinRecursiveTask left = new MyForkJoinRecursiveTask(start, middle);
                MyForkJoinRecursiveTask right = new MyForkJoinRecursiveTask(middle + 1, end);

                left.fork();
                right.fork();

                return left.join() + right.join();
            }
        }
    }
}
