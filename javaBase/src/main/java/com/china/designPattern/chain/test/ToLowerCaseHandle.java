package com.china.designPattern.chain.test;

public class ToLowerCaseHandle extends DataHandle {

    @Override
    public Data dispose(Data data) {
        data.setVal(data.getVal().toLowerCase());
        return data;
    }
}
