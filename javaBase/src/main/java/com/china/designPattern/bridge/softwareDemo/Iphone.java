package com.china.designPattern.bridge.softwareDemo;

public class Iphone extends Phone {
    public Iphone(Software software) {
        super(software);
    }

    @Override
    public void run() {
        System.out.println("iphone run software");
        getSoftware().run();
    }
}