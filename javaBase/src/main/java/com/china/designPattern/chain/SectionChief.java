package com.china.designPattern.chain;

public class SectionChief extends Handler {
    @Override
    public String dispose(String user, double fee) {
        if(fee < 1000){
            System.out.println("小主管 给了 "+user+" "+fee+"元");
        }else if (super.getNextHandler() == null){
            System.out.println("谁都处理不了 "+user+" 要 "+fee+"元的事情");
        }else {
            super.getNextHandler().dispose(user,fee);
        }
        return null;
    }
}