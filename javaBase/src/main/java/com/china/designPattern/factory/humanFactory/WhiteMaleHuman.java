package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 男性白种人
 */
public class WhiteMaleHuman extends YellowHuman {

    @Override
    public void sex() {
        System.out.println("该白种人的性别为男...");
    }

}