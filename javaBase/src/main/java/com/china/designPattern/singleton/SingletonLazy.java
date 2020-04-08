package com.china.designPattern.singleton;

/**
 * 懒汉式单例
 * */
public class SingletonLazy {

    private SingletonLazy(){}

    private static volatile SingletonLazy INSTANCE = null;

    public static SingletonLazy getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonLazy.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonLazy();
                }
            }
        }
        return INSTANCE;
    }
}
