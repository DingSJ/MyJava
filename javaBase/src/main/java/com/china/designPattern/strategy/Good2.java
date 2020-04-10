package com.china.designPattern.strategy;


import java.util.function.Function;

public class Good2 {
    private String name;
    private double price;

    private Discount discount;

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price * discount.discount();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Good2(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Good2(String name, double price, Function<Double,Double> function) {
        this.name = name;
        this.price = function.apply(price);
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
