package com.china.designPattern.bridge.fileDemo2;

public class LoadFileFromSqlServer implements ILoadFile {

    @Override
    public String loadFile() {
        return "SqlServer-Data";
    }
}
