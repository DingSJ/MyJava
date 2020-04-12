package com.china.designPattern.visitor.test;

public class DataFormatToTXT implements DataFormat {

    @Override
    public void visit(Data data) {
        System.out.println("txt - 格式");
    }
}
