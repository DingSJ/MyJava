package com.china.designPattern.singleton;

public class SingletonInnerClass {
    private SingletonInnerClass(){}

    public static Resource2 getInstance(){
        return Resource2.getInstance();
    }
}
class Resource2 {
    private static Resource2 instance =null;
    public static Resource2 getInstance() {
        instance = new Resource2();
        return instance;
    }
}