package com.china.designPattern.builder;

/**
 * 建造者模式的定义
 *  将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示，
 *  这是官方定义，通俗的说就是：建造者模式就是如何一步步构建一个包含多个组成部件的对象，
 *  相同的构建过程可以创建不同的产品
 *
 * 建造者模式的特点
        建造者模式是一种创建型模式，适用于那些流程固定「顺序不一定固定」，
        建造的目标对象会有所改变这种场景「比如画一条狗，这个目标不变，但是不同的是有黄狗，胖狗，瘦狗等」，
        还有一种场景是代替多参数构造器
 *
 * 建造者模式的作用
        1、用户不知道对象的建造过程和细节就可以创建出复杂的对象「屏蔽了建造的具体细节」
        2、用户只需给出复杂对象的内容和类型可以创建出对象
        3、建造者模工按流程一步步的创建出复杂对象
 * */
public class Client {

    public static void main(String[] args) {
        Builder builder = new WelcomeBuilder();
        Director director = new Director(builder);
        director.construct("toAddress@126.com", "fromAddress@126.com");
        
    }

}