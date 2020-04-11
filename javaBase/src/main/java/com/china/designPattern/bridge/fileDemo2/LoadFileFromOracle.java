package com.china.designPattern.bridge.fileDemo2;

public class LoadFileFromOracle implements ILoadFile {

    @Override
    public String loadFile() {
        return "Oracle-Data";
    }
}
