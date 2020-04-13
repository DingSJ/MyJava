package com.china.designPattern.observer.test2;

public class ConcreteSubject extends Subject{

    // 具体业务
    public void doSomething() {
        super.notifyObservers();
    }
}