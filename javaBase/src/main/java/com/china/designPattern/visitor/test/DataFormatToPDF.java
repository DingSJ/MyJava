package com.china.designPattern.visitor.test;

public class DataFormatToPDF implements DataFormat {

    @Override
    public void visit(Data data) {
        System.out.println("pdf - 格式");
    }
}
