package com.china.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyDynamicHandler implements InvocationHandler {
    private Object object;

    public MyDynamicHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preHandler();
        Object obj = method.invoke(object, args);
        postHandler();
        return obj;
    }

    private void preHandler() {
        System.out.println("代理处理前......");
    }
    private void postHandler() {
        System.out.println("代理处理后......");
    }
}
