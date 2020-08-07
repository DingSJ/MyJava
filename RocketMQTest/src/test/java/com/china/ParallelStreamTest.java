package com.china;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelStreamTest {
    public static void main(String[] args) {

        Random random = new Random();

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);
        intList.add(9);
        intList.add(10);

        long start = System.currentTimeMillis();

        AtomicInteger atoInt = new AtomicInteger();
        intList.parallelStream().forEach(val -> sleep(val, atoInt));
        long end = System.currentTimeMillis();
        System.out.println("All : " + (end - start) / 1000);
        System.out.println("Total : " + atoInt.get());

//        TimeUnit.SECONDS.sleep(20);
    }

    public static void sleep(int val, AtomicInteger atoInt) {
        try {
            System.out.println("--- " + val + "  start");
            System.out.println(Thread.currentThread().getName() + " : --- " + val);
            TimeUnit.SECONDS.sleep(val);
            atoInt.getAndAdd(val);
            System.out.println("--- " + val + "  end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
