package com.china.designPattern.template.test;

/**
 * 模板方式实现数据库数据导出
 * */
public class DataExportTest {
    public static void main(String[] args) {

        // Mysql -> Excel
        ExportDataMysqlToExcel export1 = new ExportDataMysqlToExcel();
        export1.run();

        // Mysql -> Txt
        ExportDataMysqlToTxt export2 = new ExportDataMysqlToTxt();
        export2.run();


    }
}
