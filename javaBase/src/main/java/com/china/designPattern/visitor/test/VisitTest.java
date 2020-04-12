package com.china.designPattern.visitor.test;

import java.util.ArrayList;
import java.util.List;

public class VisitTest {
    public static void main(String[] args) {
        List<Data> data = new ArrayList<>();

        Data data1 = new MysqlData("mysql-hello");
        data1.accept(new DataFormatToPDF());

        Data data2 = new OracleData("Oracle-hello");
        data2.accept(new DataFormatToTXT());

        data.add(data1);
        data.add(data2);

        System.out.println(data);
    }
}
