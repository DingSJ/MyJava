package com.china.designPattern.chain.test;

public class Data {
    private String val;

    public void setVal(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "Data{" +
                "val='" + val + '\'' +
                '}';
    }
}
