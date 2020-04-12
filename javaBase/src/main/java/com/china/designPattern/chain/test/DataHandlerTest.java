package com.china.designPattern.chain.test;

/**
 * 对一个数据加工处理
 *   handler  -> handler -> handler -> ....
 * */
public class DataHandlerTest {
    public static void main(String[] args) {
        Data data = new Data();
        data.setVal("ASDgdffgdfsfsfe df");


        ToLowerCaseHandle lower = new ToLowerCaseHandle();
        ToUpperCaseHandle upper = new ToUpperCaseHandle();
        lower.setHandle(upper);
        Data l = lower.dispose(data);
        System.out.println(l);
        Data u = upper.dispose(data);
        System.out.println(u);
    }
}
