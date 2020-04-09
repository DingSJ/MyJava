package com.china.strategy;

public class Discount95 implements Discount {
    @Override
    public double discount() {
        System.out.println("95折");
        return 0.95d;
    }
}
