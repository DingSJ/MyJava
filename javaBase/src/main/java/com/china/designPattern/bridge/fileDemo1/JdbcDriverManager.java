package com.china.designPattern.bridge.fileDemo1;

public class JdbcDriverManager {

    public String connectAndReadOracle(){
        // 模拟连接Oracle数据库的代码
        System.out.println("已成功连接到Oracle数据库");
        // 模式 省略 从数据库中获取内容的代码
        String content = "已成功从Oracle数据库中读取到了内容";
        return content;
    }

    public String connectAndReadMySql(){
        // 模拟连接MySql数据库的代码
        System.out.println("已成功连接到MySql数据库");
        // 模式 省略 从数据库中获取内容的代码
        String content = "已成功从MySql数据库中读取到了内容";
        return content;
    }

    public String connectAndReadSqlServer(){
        // 模拟连接Sql Server数据库的代码
        System.out.println("已成功连接到Sql Server数据库");
        // 模式 省略 从数据库中获取内容的代码
        String content = "已成功从Sql Server数据库中读取到了内容";
        return content;
    }
}