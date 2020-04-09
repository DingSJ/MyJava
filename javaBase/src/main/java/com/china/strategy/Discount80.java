package com.china.strategy;

public class Discount80 implements Discount {
    @Override
    public double discount() {
        System.out.println("打八折");
        return 0.8d;
    }
}
