package com.china.designPattern.memento.test3;

public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker(originator);
        //改变状态
        originator.setState("State 0");
        //建立一个检查点
        caretaker.createMemento();
        //改变状态
        originator.setState("State 1");
        //建立一个检查点
        caretaker.createMemento();
        //改变状态
        originator.setState("State 2");;
        //建立一个检查点
        caretaker.createMemento();
        //改变状态
        originator.setState("State 3");
        //建立一个检查点
        caretaker.createMemento();
        //改变状态
        originator.setState("State 4");
        //建立一个检查点
        caretaker.createMemento();
        //打印出所有的检查点
        originator.pringStates();
        System.out.println("---------恢复状态到某一个检查点----------");
        //恢复到第二个检查点
        caretaker.restoreMemento(1);
        //打印出所有的检查点.
        originator.pringStates();
    }
}