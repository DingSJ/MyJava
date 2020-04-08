package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 女性创建工厂
 */
public class FemaleHumanFactory extends AbstractHumanFactory {

    // 创造女性黄种人
    @Override
    public Human createYellowHuman() {
        return super.createHuman(HumanEnum.YellowFemaleHuman);
    }

    // 创造女性白种人
    @Override
    public Human createWhiteHuman() {
        return super.createHuman(HumanEnum.WhiteFemaleHuman);
    }

    // 创造女性黑种人
    @Override
    public Human createBlackHuman() {
        return super.createHuman(HumanEnum.BlackFemaleHuman);
    }

}
