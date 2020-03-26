package com.china.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 集中求和的速度對比
 * */
public class ForkJoinTest {

    private static final int LIMIT = 10000;
    private static final long TOTAL = 10_000_000L;

    public static void main(String[] args) {
        forkJoinTest();
        normalTest();
        iteratorTest();
        longStreamRangeTest();
        longStreamRangeClosedTest();
        parallelTest();

        /*
            forkJoinTest - 总耗时：161, 计算耗时：157, sum:50000005000000
            normalTest - 耗时：19, sum:50000005000000
            iteratorTest - 耗时：410, sum:50000005000000
            longStreamRangeTest - 耗时：47, sum:50000005000000
            streamRangeTest - 耗时：87, sum:50000005000000
            parallelTest - 耗时：46, sum:50000005000000
        */
    }

    private static void parallelTest() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, TOTAL).parallel().reduce(0L, Long::sum);
        long duration = System.currentTimeMillis() - start;
        System.out.printf("parallelTest - 耗时：%d, sum:%d\n", duration, sum);
    }

    private static void longStreamRangeClosedTest() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, TOTAL).reduce(0L, Long::sum);
        long duration = System.currentTimeMillis() - start;
        System.out.printf("streamRangeTest - 耗时：%d, sum:%d\n", duration, sum);
    }

    private static void longStreamRangeTest() {
        long start = System.currentTimeMillis();
        // range (0,100)  rangeClesed [0,100]
        long sum = LongStream.range(1L, TOTAL+1).reduce(0L, Long::sum);
        long duration = System.currentTimeMillis() - start;
        System.out.printf("longStreamRangeTest - 耗时：%d, sum:%d\n", duration, sum);
    }

    private static void iteratorTest() {
        long start = System.currentTimeMillis();
        long sum = Stream.iterate(1L, i -> i + 1).limit(TOTAL).reduce(0L, Long::sum);
        long duration = System.currentTimeMillis() - start;
        System.out.printf("iteratorTest - 耗时：%d, sum:%d\n", duration, sum);
    }

    private static void normalTest() {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i <= TOTAL; i++) {
            sum += i;
        }
        long duration = System.currentTimeMillis() - start;
        System.out.printf("normalTest - 耗时：%d, sum:%d\n", duration, sum);
    }

    private static void forkJoinTest() {
        long start = System.currentTimeMillis();
        ForkJoinPool joinPool = new ForkJoinPool();
        long s = System.currentTimeMillis();
        Long invoke = joinPool.invoke(new MySum(0, TOTAL));
        long e = System.currentTimeMillis() - s;
        joinPool.awaitQuiescence(10, TimeUnit.SECONDS);
        long duration = System.currentTimeMillis() - start;
        System.out.printf("forkJoinTest - 总耗时：%d, 计算耗时：%d, sum:%d\n", duration, e, invoke);

    }

    static class MySum extends RecursiveTask<Long> {
        private long start;
        private long end;

        public MySum(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start < LIMIT) {
                return LongStream.rangeClosed(start,end).sum();
            }else {
                long middle = (end + start) / 2;
//                System.out.println("middle : " + middle);
                MySum leftSum = new MySum(start, middle);
                MySum rightSum = new MySum(middle + 1, end);
                leftSum.fork();
                rightSum.fork();

                return leftSum.join() + rightSum.join();
            }
        }
    }

}
