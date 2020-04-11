package com.china.designPattern.bridge.fileDemo1;

public abstract class FileExportAbstraction {

    protected FileExportImpl fileSouce;

    public void setFileSource(FileExportImpl fileSouce){
        this.fileSouce = fileSouce;
    }

    public abstract void exportFile();

}