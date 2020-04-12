package com.china.designPattern.chain.test;

abstract class DataHandle {

    protected DataHandle handle;

    public void setHandle(DataHandle handle) {
        this.handle = handle;
    }

    public abstract Data dispose(Data data);
}