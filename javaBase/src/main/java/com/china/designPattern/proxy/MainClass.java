package com.china.designPattern.proxy;

import java.lang.reflect.Proxy;

public class MainClass {
    public static void main(String[] args) {

        DynamicProxyHandler handler = new DynamicProxyHandler(new RealSubject());

        // 代理类
        ISubject proxySubject = (ISubject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(), new Class[]{ISubject.class}, handler);
        // 代理类调用方法
        proxySubject.request();

    }
}