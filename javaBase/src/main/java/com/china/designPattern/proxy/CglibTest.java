package com.china.designPattern.proxy;

public class CglibTest {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        RealSubject subject = (RealSubject)proxy.getProxyObj(RealSubject.class);
        subject.request();
    }
}
