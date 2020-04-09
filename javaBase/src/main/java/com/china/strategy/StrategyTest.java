package com.china.strategy;

/**
 * 商品折扣 ，策略模式实现 打八折，打五折，不打折
 * */
public class StrategyTest {
    public static void main(String[] args) {

        Good apple = new Good("红富士", 6.66, new Discount100());
        System.out.println("apple: " + apple);

        apple.setDiscount(new Discount95());
        System.out.println("apple95: " + apple.getPrice());
    }
}
