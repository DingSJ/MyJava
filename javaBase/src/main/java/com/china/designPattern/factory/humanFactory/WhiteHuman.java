package com.china.designPattern.factory.humanFactory;

public abstract class WhiteHuman implements Human {

    @Override
    public void laugh() {
        System.out.println("白种人会笑...");
    }

    @Override
    public void cry() {
        System.out.println("白种人会哭...");
    }

    @Override
    public void talk() {
        System.out.println("白种人说话，一般说的都是单字节...");
    }

}