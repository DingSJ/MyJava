package com.china.designPattern.factory.humanFactory;

/**
 * 创建对象
 * 男（黑、白、黄）、女（黑、白、黄）
 */
public class PeopleTest {

    public static void main(String[] args) {
        // 男性生产线
        HumanFactory maleHumanFactory = new MaleHumanFactory();

        // 女性生产线
        HumanFactory femaleHumanFactory = new FemaleHumanFactory(); 
        
        // 生产线建立完毕，开始生产人了:
        Human maleYellowHuman = maleHumanFactory.createYellowHuman();

        Human femaleYellowHuman = femaleHumanFactory.createBlackHuman();

        maleYellowHuman.cry();
        maleYellowHuman.laugh();
        femaleYellowHuman.sex();
        femaleYellowHuman.cry();
        femaleYellowHuman.laugh();

        
    }
    
}
