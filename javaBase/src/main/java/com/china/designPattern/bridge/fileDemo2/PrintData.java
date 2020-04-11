package com.china.designPattern.bridge.fileDemo2;

/**
 * 文件输出顶层类
 * 因为要注入文件读取的实例，所以设计成抽象类
 * */
public abstract class PrintData {
    protected ILoadFile loadFile;

    public ILoadFile getLoadFile() {
        return loadFile;
    }
    public void setLoadFile(ILoadFile loadFile) {
        this.loadFile = loadFile;
    }

    /** 子类实现该接口具体处理数据的输出方式 */
    public abstract void print();
}
