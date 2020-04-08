package com.china.designPattern.singleton;

/**
 * 懒汉式单例
 * */
public class SingletonHungry {

    private SingletonHungry(){}

    private static volatile SingletonHungry INSTANCE = new SingletonHungry();

    public static SingletonHungry getInstance() {
        return INSTANCE;
    }
}
