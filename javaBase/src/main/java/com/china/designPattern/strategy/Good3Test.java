package com.china.designPattern.strategy;

public class Good3Test {
    public static void main(String[] args) {

        Good3 apple = new Good3("Hongfushi", 8.88, () -> new Discount80().discount());
        System.out.println(apple);
        Good3 apple1 = new Good3("Hongfushi", 8.88, () -> new Discount95().discount());
        System.out.println(apple1);

    }
}
