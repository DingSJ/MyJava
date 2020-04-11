package com.china.designPattern.flyweight.composite;

import com.china.designPattern.flyweight.Flyweight;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<String> compositeState = new ArrayList<String>();
        compositeState.add("辣椒炒肉");
        compositeState.add("牛肉");
        compositeState.add("鸡肉");
        compositeState.add("辣椒炒肉");
        compositeState.add("牛肉");
 
        FlyweightFactory flyFactory = new FlyweightFactory();
        Flyweight compositeFly1 = flyFactory.factory(compositeState);
        Flyweight compositeFly2 = flyFactory.factory(compositeState);
        compositeFly1.operation("汤高点菜");//外蕴状态是同一个
        System.out.println();
        compositeFly2.operation("Zhang_Test");
 
        System.out.println("---------------------------------");
        System.out.println("复合享元模式是否可以共享对象：" + (compositeFly1 == compositeFly2));
 
        String state = "牛肉";
        Flyweight fly1 = flyFactory.factory(state);
        Flyweight fly2 = flyFactory.factory(state);
        System.out.println("单纯享元模式是否可以共享对象：" + (fly1 == fly2));
    }
 
}