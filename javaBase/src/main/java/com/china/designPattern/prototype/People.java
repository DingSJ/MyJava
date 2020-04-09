package com.china.designPattern.prototype;

import java.util.List;

/**
 * 原型模式测试实体
 *  基本类型
 *
 *  使用原型模式复制对象不会调用类的构造方法。因为对象的复制是通过调用Object类的clone方法来完成的，
 *  它直接在内存中复制数据，因此不会调用到类的构造方法。不但构造方法中的代码不会执行，甚至连访问权限都对原型模式无效。
 *  还记得单例模式吗？单例模式中，只要将构造方法的访问权限设置为private型，就可以实现单例。
 *  但是clone方法直接无视构造方法的权限，所以，单例模式与原型模式是冲突的，在使用时要特别注意。
 *
 *
 * 深拷贝与浅拷贝。
 *      Object类的clone方法只会拷贝对象中的基本的数据类型，
 *      对于数组、容器对象、引用对象等都不会拷贝，这就是浅拷贝。如果要实现深拷贝，
 *      必须将原型模式中的数组、容器对象、引用对象等另行拷贝。
 *
 *     ### 浅拷贝： 拷贝对象的引用类型和原来的对象指向同一个地方

 序列化
     把对象写到流里的过程是序列化(Serialization)过程
     而把对象从流中读出来的过程则叫反序列化(Deserialization)过程

 * */
public class People implements Cloneable{
    private String name;

    private int age;

    private People friend;

    private List<String> hobby;

    private List<Demo> demos;

    public People() {
        System.out.println("构造函数运行..................");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public People getFriend() {
        return friend;
    }

    public void setFriend(People friend) {
        this.friend = friend;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public List<Demo> getDemos() {
        return demos;
    }

    public void setDemos(List<Demo> demos) {
        this.demos = demos;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friend=" + friend +
                ", hobby=" + hobby +
                ", demos=" + demos +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
