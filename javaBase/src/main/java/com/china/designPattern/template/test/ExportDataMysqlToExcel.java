package com.china.designPattern.template.test;

public class ExportDataMysqlToExcel extends DataExportTemplate {

    @Override
    protected void doDataExport(String data) {
        System.out.println("数据从Mysql导出到Excel ：" + data);
    }

    @Override
    protected String doGetData() {
        return "Mysql-Data-To-Excel";
    }
}
