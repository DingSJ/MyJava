package com.china.designPattern.chain;

public class StaffMember extends Handler {
    @Override
    public String dispose(String user, double fee) {
        if(fee < 500){
            System.out.println("职员 给了 "+user+" "+fee+"元");
        }else if (super.getNextHandler() == null){
            System.out.println("谁都处理不了 "+user+" 要 "+fee+"元的事情");
        }else {
            super.getNextHandler().dispose(user,fee);
        }
        return null;
    }
}