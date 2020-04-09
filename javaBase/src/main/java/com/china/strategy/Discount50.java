package com.china.strategy;

public class Discount50 implements Discount {
    @Override
    public double discount() {
        System.out.println("打5折");
        return 0.5d;
    }
}
