package com.china.designPattern.observer.javaObserver;

import java.util.Observable;
import java.util.Observer;

public class ReaderB implements Observer{

    public ReaderB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        System.out.println("我是读者B,收到了新书:" + arg.toString());
    }

}