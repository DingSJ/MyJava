package com.china.designPattern.bridge.fileDemo1;

public class Client {

    public static void main(String[] args) {
        FileExportImpl fileOracle = new FileExportFromOracle();
        FileExportImpl fileMySql = new FileExportFromMySql();
        FileExportImpl fileSqlServer = new FileExportFromSqlServer();

        FileExportAbstraction fileTxtExport = new TxtFileExport();
        FileExportAbstraction fileXmlExport = new XmlFileExport();
        FileExportAbstraction filePdfExport = new PdfFileExport();

        // 如果我们要从Oracle中导出xml格式的数据
        fileXmlExport.setFileSource(fileOracle);
        fileXmlExport.exportFile();

        System.out.println("--------------------\n");
        // 如果我们要从Oracle中导出txt格式的数据
        fileTxtExport.setFileSource(fileOracle);
        fileTxtExport.exportFile();

        System.out.println("--------------------\n");
        // 如果我们要从MySql中导出pdf格式的数据
        filePdfExport.setFileSource(fileMySql);
        filePdfExport.exportFile();
    }

}