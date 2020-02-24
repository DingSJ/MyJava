package com.china.test.Class;

import org.junit.Test;

@TestAnno
public class ClassDemo {

    private String name;
    public int age;
    private static String data;
    static {
        data = "My Class Static Init....";
    }

    public ClassDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public ClassDemo() {
    }

    public String hello(String name) {
        System.out.println("Hello :" + name);
        System.out.println("age :" + age);
        return name;
    }
}
