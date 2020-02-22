package com.china.test.thread2.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 单例： 饿汉式，线程安全
 * */
public class SingletonDemo1 {
    private static final SingletonDemo1 INSTANCE = new SingletonDemo1();
    private SingletonDemo1(){

    }

    public static SingletonDemo1 getInstance() {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return INSTANCE;
    }

}
