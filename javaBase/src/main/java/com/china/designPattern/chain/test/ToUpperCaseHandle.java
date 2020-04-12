package com.china.designPattern.chain.test;

public class ToUpperCaseHandle extends DataHandle {
    @Override
    public Data dispose(Data data) {
        data.setVal(data.getVal().toUpperCase());
        return data;
    }
}
