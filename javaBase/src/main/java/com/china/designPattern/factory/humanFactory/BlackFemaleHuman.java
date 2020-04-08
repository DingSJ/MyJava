package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 女性黑种人
 */
public class BlackFemaleHuman extends YellowHuman {

    @Override
    public void sex() {
        System.out.println("该黑种人的性别为女...");
    }

}