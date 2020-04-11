package com.china.designPattern.bridge.fileDemo2;

/**
 * 策略模式：抽象和实现分离
 * 用设计模式实现 从不同的数据库读取数据 以不同的方式写入到文件
 *
 *      MySql                  txt
 *      Oracle        ----->   excel
 *      SqlServer              pdf
 *
 */
public class BridgeTest {
    public static void main(String[] args) {

        // 从 Mysql 中读取数据写入到 Excel 里面
        ILoadFile loadFile = new LoadFileFromMysql();
        PrintData printToExcel = new PrintDataToExcel();
        printToExcel.setLoadFile(loadFile);
        // 处理数据
        printToExcel.print();

        // 从 Mysql 中读取数据写入到 TXT 里面
        ILoadFile loadFile2 = new LoadFileFromMysql();
        PrintData printToTXT = new PrintDataToTxt();
        printToTXT.setLoadFile(loadFile2);
        // 处理数据
        printToTXT.print();

        // 从 Oracle 中读取数据写入到 PDF 里面
        ILoadFile loadFile3 = new LoadFileFromOracle();
        PrintData printToPDF = new PrintDataToPDF();
        printToPDF.setLoadFile(loadFile3);
        // 处理数据
        printToPDF.print();
    }
}
