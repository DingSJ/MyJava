package com.china.designPattern.visitor.test;


public class MysqlData extends Data{
    public MysqlData(String msg) {
        super(msg);
    }

    @Override
    String accept(DataFormat format) {
        format.visit(this);
        return "Mysql - msg";
    }
}
