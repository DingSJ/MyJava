package com.china.strategy;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Good3 {
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

    public Good3(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Good3(String name, double price, Supplier<Double> supplier) {
        this.name = name;
        this.price = price * supplier.get();
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
