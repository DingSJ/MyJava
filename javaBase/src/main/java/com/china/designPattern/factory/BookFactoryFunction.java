package com.china.designPattern.factory;

/**
 * 工厂方法模式 - 类创建型
 * 将类的实例化（具体产品的创建）延迟到工厂类的子类（具体工厂）中完成，即由子类来决定应该实例化（创建）哪一个类。
 * 工厂一旦需要生产新产品就需要修改工厂类的方法逻辑，违背了“开放 - 关闭原则
 */
public class BookFactoryFunction {

    public static Book getJavaBook() {
        return new JavaBook();
    }
    public static Book getRedisBook() {
        return new RedisBook();
    }
    public static Book getPythonBook() {
        return new PythonBook();
    }
    public static Book getSpringBook() {
        return new SpringBook();
    }
}
