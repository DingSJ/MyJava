package com.china.designPattern.bridge.softwareDemo;

public class HuaWei extends Phone {
    public HuaWei(Software software) {
        super(software);
    }

    @Override
    public void run() {
        System.out.println("HuaWei run software");
        getSoftware().run();
    }
}