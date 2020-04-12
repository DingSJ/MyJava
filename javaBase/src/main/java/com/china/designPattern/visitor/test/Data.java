package com.china.designPattern.visitor.test;


public abstract class Data {
    protected String msg;

    public Data(String msg) {
        this.msg = msg;
    }

    abstract String accept(DataFormat format);
}
