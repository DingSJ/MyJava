package com.china.test.thread2.singleton;

public enum SingletonEnum {
    INSTANCE;

    private final SingletonDemo6 instance;
    SingletonEnum(){
        instance = new SingletonDemo6();
    }

    public SingletonDemo6 getInstance() {
        return instance;
    }
}
