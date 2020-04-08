package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 女性黄种人
 */
public class YellowFemaleHuman extends YellowHuman {

    @Override
    public void sex() {
        System.out.println("该黄种人的性别为女...");
    }

}