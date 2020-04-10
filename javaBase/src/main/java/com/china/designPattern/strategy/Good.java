package com.china.designPattern.strategy;

public class Good {
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

    public Good(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Good(String name, double price, Discount discount) {
        this.name = name;
        this.discount = discount;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
