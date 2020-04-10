package com.china.designPattern.adapter;

// 适配器类，直接关联被适配类，同时实现标准接口
class Adapter2 implements Target{
    // 直接关联被适配类
    private Adaptee adaptee;

    // 可以通过构造函数传入具体需要适配的被适配类对象
    public Adapter2 (Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void Request() {
        // 这里是使用委托的方式完成特殊功能
        this.adaptee.SpecificRequest();
    }
}

class Main{
    public static void main(String[] args) {

        // 类适配器
        Target target = new Adapter();
        target.Request();

        // 对象适配器
        Adaptee adaptee = new Adaptee();
        Target target1 = new Adapter2(adaptee);
        target1.Request();
    }
}