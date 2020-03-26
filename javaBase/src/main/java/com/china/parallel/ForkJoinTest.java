package com.china.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ForkJoinTest {

    private static final int LIMIT = 1000;

    public static void main(String[] args) {
        ForkJoinPool joinPool = new ForkJoinPool(20);
        Integer invoke = joinPool.invoke(new MySum(0, 10_000_0));
        joinPool.awaitQuiescence(10, TimeUnit.SECONDS);
        System.out.println(invoke);

    }

    static class MySum extends RecursiveTask<Integer> {
        private int start;
        private int end;

        public MySum(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start < LIMIT) {
                return IntStream.rangeClosed(start,end).sum();
            }else {
                int middle = (end + start) / 2;
                System.out.println("middle : " + middle);
                MySum leftSum = new MySum(start, middle);
                MySum rightSum = new MySum(middle + 1, end);
                leftSum.fork();
                rightSum.fork();

                return leftSum.join() + rightSum.join();
            }
        }
    }

}
