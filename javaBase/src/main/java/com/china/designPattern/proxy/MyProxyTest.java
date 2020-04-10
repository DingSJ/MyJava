package com.china.designPattern.proxy;

import java.lang.reflect.Proxy;

public class MyProxyTest {
    public static void main(String[] args) {

        MyDynamicHandler handler = new MyDynamicHandler(new MyHelloProxy());
        MyInterface hello = (MyInterface)Proxy.newProxyInstance(MyHelloProxy.class.getClassLoader(), new Class[]{MyInterface.class}, handler);
        hello.sayHello();
    }
}
