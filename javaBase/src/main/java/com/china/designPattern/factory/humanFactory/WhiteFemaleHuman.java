package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 女性白种人
 */
public class WhiteFemaleHuman extends YellowHuman {

    @Override
    public void sex() {
        System.out.println("该白种人的性别为女...");
    }

}