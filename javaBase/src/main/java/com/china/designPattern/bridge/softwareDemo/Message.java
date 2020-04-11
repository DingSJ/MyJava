package com.china.designPattern.bridge.softwareDemo;

public class Message implements Software {
    @Override
    public void run() {
        System.out.println("Software message");
    }
}