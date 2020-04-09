package com.china.strategy;

public class Discount100 implements Discount {
    @Override
    public double discount() {
        System.out.println("不打折");
        return 1d;
    }
}
