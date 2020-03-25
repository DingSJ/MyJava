package com.china.parallel;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 几种加法的速度比较
 */
public class ParallelDemo {

    /** Result:
        availableProcessors : 4
        normalAdd: 11.1
        Long-Object: 246.8
        long 296.0
        long-parallel 1480.8
        long-parallel2 1189.0
        long-parallel-LongStream 36.8
        long-parallel-LongStream-rangeClosed 25.3
    */
    public static void main(String[] args) {
        System.out.println("availableProcessors : " + Runtime.getRuntime().availableProcessors());

        System.out.println("normalAdd: " + measureSumPerformance(ParallelDemo::normalAdd, 10_000_000L));
        System.out.println("Long-Object: " + measureSumPerformance(ParallelDemo::iteratorStream, 10_000_000L));
        System.out.println("long " + measureSumPerformance(ParallelDemo::iteratorStream2, 10_000_000L));
        System.out.println("long-parallel " + measureSumPerformance(ParallelDemo::iteratorStream3, 10_000_000L));
        System.out.println("long-parallel2 " + measureSumPerformance(ParallelDemo::iteratorStream4, 10_000_000L));
        System.out.println("long-parallel-LongStream " + measureSumPerformance(ParallelDemo::iteratorStream5, 10_000_000L));
        System.out.println("long-parallel-LongStream-rangeClosed " + measureSumPerformance(ParallelDemo::iteratorStream6, 10_000_000L));
    }

    public static double measureSumPerformance(Function<Long, Long> sumFun, Long limit) {
        long[] times = new long[10];
        Stream.iterate(1, i -> i + 1).limit(10).forEach(i -> {
            int index = i - 1;
            long start = System.currentTimeMillis();
            long result = sumFun.apply(limit);
            times[index] = System.currentTimeMillis() - start;
//            System.out.printf("result : %d, times: %d\n", result, times[index]);
        });
        return Arrays.stream(times).average().getAsDouble();
    }

    public static long iteratorStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    public static long iteratorStream2(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).mapToLong(Long::longValue).reduce(0L, Long::sum);
    }

    public static long iteratorStream3(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).mapToLong(Long::longValue).parallel().reduce(0L, Long::sum);
    }

    public static long iteratorStream4(long limit) {
        return Stream.iterate(1L, i -> i + 1).parallel().limit(limit).mapToLong(Long::longValue).reduce(0L, Long::sum);
    }

    public static long iteratorStream5(long limit) {
        return LongStream.range(1, limit).parallel().reduce(0L, Long::sum);
    }

    public static long iteratorStream6(long limit) {
        return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);
    }

    public static long normalAdd(long limit) {
        long sum = 0L;
        for (long i = 0; i < limit; i++) {
            sum += i;
        }
        return sum;
    }


}
