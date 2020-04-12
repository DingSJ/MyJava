package com.china.designPattern.chain;

public abstract class Handler {
    protected Handler nextHandler = null;
 
    public Handler getNextHandler() {
        return nextHandler;
    }
 
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
     
    public abstract String dispose(String user , double fee);
}