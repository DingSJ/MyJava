package com.china.designPattern.proxy;

import java.lang.reflect.Proxy;

public class MainClass2 {
    public static void main(String[] args) {
        // 委托类
        ISubject mRealSubject = new RealSubject();
        // 委托类classLoader
        ClassLoader mClassLoader = mRealSubject.getClass().getClassLoader();
        // 委托类对应的ProxyHandler
        DynamicProxyHandler mProxyHandler = new DynamicProxyHandler(mRealSubject);
        Class[] mClasses = new Class[]{ISubject.class};
        // 代理类
        ISubject proxySubject = (ISubject) Proxy.newProxyInstance(mClassLoader, mClasses, mProxyHandler);
        // 代理类调用方法
        proxySubject.request();
        
    }
}