package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 男性黑种人
 */
public class BlackMaleHuman extends YellowHuman {

    @Override
    public void sex() {
        System.out.println("该黑种人的性别为男...");
    }

}