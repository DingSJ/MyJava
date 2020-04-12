package com.china.designPattern.template.test;

public class ExportDataMysqlToTxt extends DataExportTemplate {

    @Override
    protected void doDataExport(String data) {
        System.out.println("数据从Mysql导出到TXT ：" + data);
    }

    @Override
    protected String doGetData() {
        return "Mysql-Data";
    }
}
