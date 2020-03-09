package com.china.test.thread2.countdownlatch;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Java8Test {
    public static void main(String[] args) {

        new Thread(() -> test("ZhangSan")).start();
        System.out.println("Hehe");

        Optional.of("LiuQ").ifPresent(Java8Test::test);
        test("YONG");
    }

    public static void test(String name){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello : " + name);
    }
}
