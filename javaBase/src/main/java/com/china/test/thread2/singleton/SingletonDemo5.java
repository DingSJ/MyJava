package com.china.test.thread2.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 静态内部类
 * */
public class SingletonDemo5 {
    private boolean flag = Boolean.FALSE;
    private SingletonDemo5(){
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("构造函数睡眠1秒");
                TimeUnit.SECONDS.sleep(1);
            }
            this.flag = Boolean.TRUE;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("构造函数初始化完成");
    }

    public boolean getFlag(){
        return this.flag;
    }

    private static class SingletonInstance{
//        public static SingletonDemo5 INSTANCE = new SingletonDemo5();

        public static SingletonDemo5 INSTANCE;
        static {
            INSTANCE = new SingletonDemo5();
        }
    }

    public static SingletonDemo5 getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
