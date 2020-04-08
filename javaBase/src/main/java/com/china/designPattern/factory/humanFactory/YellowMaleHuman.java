package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 男性黄种人
 */
public class YellowMaleHuman extends YellowHuman {

    @Override
    public void sex() {
        System.out.println("该黄种人的性别为男...");
    }

}