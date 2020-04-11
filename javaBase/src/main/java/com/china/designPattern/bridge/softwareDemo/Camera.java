package com.china.designPattern.bridge.softwareDemo;

public class Camera implements Software {
    @Override
    public void run() {
        System.out.println("Software camera");
    }
}