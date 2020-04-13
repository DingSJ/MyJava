package com.china.designPattern.observer.test2;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer = new ConcreteObserver();
        subject.addObserver(observer);
        subject.doSomething();
    }
}