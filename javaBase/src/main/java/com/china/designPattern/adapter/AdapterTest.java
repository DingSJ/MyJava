package com.china.designPattern.adapter;

/**
 * 类适配器
 * <p>
 * Adapter 类继承Adaptee （被适配类），同时实现Target 接口（因为 Java 不支持多继承，
 * 所以只能通过接口的方法来实现多继承），在 Client 类中我们可以根据需要选择并创建任一种符合需求的子类，
 * 来实现具体功能。
 * <p>
 * 对象适配器
 * <p>
 * 不使用多继承或继承的方式，而是使用直接关联，或者称为委托的方式。
 * <p>
 * 区别
 * <p>
 * 类适配器的重点在于类，是通过构造一个继承Adaptee类来实现适配器的功能；
 * 对象适配器的重点在于对象，是通过在直接包含Adaptee类来实现的，当需要调用特殊功能的时候直接使用Adapter中
 * 包含的那个Adaptee对象来调用特殊功能的方法即可。
 */

public class AdapterTest {

    public static void main(String[] args) {

        Target mAdapter = new Adapter();
        mAdapter.Request();

    }
}