package com.china.designPattern.bridge.fileDemo2;

public class LoadFileFromMysql implements ILoadFile {

    @Override
    public String loadFile() {
        return "Mysql-Data";
    }
}
