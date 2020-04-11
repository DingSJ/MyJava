package com.china.designPattern.bridge.fileDemo1;

public class XmlFileExport extends FileExportAbstraction {

    @Override
    public void exportFile() {
        String readContent = fileSouce.readContent();
        System.out.println(readContent + "，将内容导出为.xml格式");
    }
}