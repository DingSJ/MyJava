package com.china.designPattern.singleton;

import java.util.stream.IntStream;

public class SingletonTest {
    public static void main(String[] args) {

        SingletonLazy sinletonDemo = SingletonLazy.getInstance();

        IntStream.rangeClosed(0, 100).forEach(i->{
            new Thread(()->{
                SingletonLazy temp = SingletonLazy.getInstance();
                if (temp != sinletonDemo) {
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("sinletonDemo : " + sinletonDemo);
                    System.out.println("temp: " + temp);
                }
                System.out.println(Thread.currentThread().getName() + " - " + temp);
            },"My-Thread-" + i).start();
        });
    }
}
