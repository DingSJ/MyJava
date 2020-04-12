package com.china.designPattern.visitor.test;


public class OracleData extends Data{
    public OracleData(String msg) {
        super(msg);
    }

    @Override
    String accept(DataFormat format) {
        format.visit(this);
        return "Oracle - msg";
    }
}
