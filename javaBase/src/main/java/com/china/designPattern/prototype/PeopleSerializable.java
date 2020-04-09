package com.china.designPattern.prototype;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 原型模式测试实体
 * */
public class PeopleSerializable implements Serializable{
    private String name;

    private int age;

    private PeopleSerializable friend;

    private List<String> hobby;

    private List<Demo> demos;

    public PeopleSerializable() {
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

    public PeopleSerializable getFriend() {
        return friend;
    }

    public void setFriend(PeopleSerializable friend) {
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
    protected PeopleSerializable clone() throws CloneNotSupportedException {

        //      使用序列化和反序列化实现深复制
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        ObjectInputStream ois;

        PeopleSerializable personClone = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            byte[] bytes = bos.toByteArray();

            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            personClone = (PeopleSerializable) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personClone;
    }
}
