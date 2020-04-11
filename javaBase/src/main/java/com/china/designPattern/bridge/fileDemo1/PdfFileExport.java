package com.china.designPattern.bridge.fileDemo1;

public class PdfFileExport extends FileExportAbstraction {

    @Override
    public void exportFile() {
        String readContent = fileSouce.readContent();
        System.out.println(readContent + "，将内容导出为.pdf格式");
    }
}