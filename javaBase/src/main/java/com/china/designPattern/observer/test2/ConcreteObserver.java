package com.china.designPattern.observer.test2;

public class ConcreteObserver implements Observer{

    public ConcreteObserver() {
        // TODO Auto-generated constructor stub
    }
    //实现更新方法
    @Override
    public void update() {
        // TODO Auto-generated method stub
        System.out.println("我已接收到消息");
    }
}