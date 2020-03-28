package com.china.java8.inAction;

public class MeaningOfThis
{
    public final int value = 4;
    public void doIt()
    {
        int value = 6;
        Runnable r = new Runnable(){
            public final int value = 5;
            public void run(){
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }
    public static void main(String...args)
    {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}

/**
 *  答案是5，因为this指的是包含它的Runnable，而不是外面的类MeaningOfThis。
 * */