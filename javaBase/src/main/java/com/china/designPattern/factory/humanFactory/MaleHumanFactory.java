package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 男性创建工厂
 */
public class MaleHumanFactory extends AbstractHumanFactory {

    // 创造男性黄种人
    @Override
    public Human createYellowHuman() {
        return super.createHuman(HumanEnum.YellowMaleHuman);
    }

    // 创造男性白种人
    @Override
    public Human createWhiteHuman() {
        return super.createHuman(HumanEnum.WhiteMaleHuman);
    }

    // 创造男性黑种人
    @Override
    public Human createBlackHuman() {
        return super.createHuman(HumanEnum.BlackMaleHuman);
    }

}