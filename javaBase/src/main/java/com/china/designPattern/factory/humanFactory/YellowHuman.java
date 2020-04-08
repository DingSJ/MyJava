package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 
 * 为什么要修改成抽象类呢？只是YellowHuman的统称，不需要定义性别
 */
public abstract class YellowHuman implements Human {

    @Override
    public void laugh() {
        System.out.println("黄种人会笑...");
    }

    @Override
    public void cry() {
        System.out.println("黄种人会哭...");
    }

    @Override
    public void talk() {
        System.out.println("黄种人说话，一般说的都是双字节...");
    }

}