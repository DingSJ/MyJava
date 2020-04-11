package com.china.designPattern.bridge.fileDemo2;

public class PrintDataToPDF extends PrintData{

    @Override
    public void print() {
        // 获取内容
        String data = loadFile.loadFile();

        // 格式化输出
        System.out.println("写文件到 PDF 文件中: " + data);
    }
}
