package com.china.designPattern.proxy;

public class MyHelloProxy implements MyInterface {
    @Override
    public void sayHello() {
        System.out.println("Hello, Can i help you ?");
    }
}
