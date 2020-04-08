package com.china.designPattern.singleton;

/**
 * 枚举类实现单例
 * */
public enum SingletonEnum {
    INSTANCE;
    public static Resource instance = null;

    public static Resource getInstance() {
        instance = new Resource();
        instance.setName("xxx");
        return instance;
    }
}

class Resource{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                '}';
    }
}
