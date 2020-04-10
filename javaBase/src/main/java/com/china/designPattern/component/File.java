package com.china.designPattern.component;

public abstract class File {
    String name;
    public File(String name){
        this.name = name;
    }
    public abstract void display();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}