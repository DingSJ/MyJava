package com.china.designPattern.factory;

public class FactoryTest {
    public static void main(String[] args) {

        // 简单工厂
        Book javaBook = BookFactorySimple.getBook("Java");
        System.out.println(javaBook.contents());

        Book redisBook = BookFactorySimple.getBook("Redis");
        System.out.println(redisBook.contents());

        Book springBook = BookFactorySimple.getBook("Spring");
        System.out.println(springBook.contents());

        System.out.println("------------------");
        // 工厂方法
        Book javaBook1 = BookFactoryFunction.getJavaBook();
        System.out.println(javaBook1.contents());

        Book redisBook1 = BookFactoryFunction.getRedisBook();
        System.out.println(redisBook1.contents());

        Book springBook1 = BookFactoryFunction.getSpringBook();
        System.out.println(springBook1.contents());
        System.out.println("------------------");
        // 抽象工厂
        IBookFactory factory = new PaperBookFactory();
        Book javaBook2 = factory.getBook("Java");
        System.out.println(javaBook2.contents());

        Book redisBook2 = factory.getBook("Redis");
        System.out.println(redisBook2.contents());

        Book springBook3 = factory.getBook("Spring");
        System.out.println(springBook3.contents());

    }
}
