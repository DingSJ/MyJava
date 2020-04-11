package com.china.designPattern.bridge.fileDemo1;

public class FileExportFromSqlServer implements FileExportImpl {

    @Override
    public String readContent() {
        JdbcDriverManager jdbcDriver = new JdbcDriverManager();
        return jdbcDriver.connectAndReadSqlServer();
    }

}