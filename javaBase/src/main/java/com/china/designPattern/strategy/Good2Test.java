package com.china.designPattern.strategy;

public class Good2Test {
    public static void main(String[] args) {

        Good2 apple = new Good2("Hongfushi", 8.88, val -> val * 0.5d);
        System.out.println(apple);
    }
}
