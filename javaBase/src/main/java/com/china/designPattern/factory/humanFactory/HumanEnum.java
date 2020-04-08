package com.china.designPattern.factory.humanFactory;

/**
 * @Description: 
 * 世界上有哪些类型的人，列出来
 * java enum类型简单易用，尽量不要使用多态、继承等方法
 */
public enum HumanEnum {

    YellowMaleHuman("com.china.designPattern.factory.humanFactory.YellowMaleHuman"),
    YellowFemaleHuman("com.china.designPattern.factory.humanFactory.YellowFemaleHuman"),
    WhiteMaleHuman("com.china.designPattern.factory.humanFactory.WhiteMaleHuman"),
    WhiteFemaleHuman("com.china.designPattern.factory.humanFactory.WhiteFemaleHuman"),
    BlackMaleHuman("com.china.designPattern.factory.humanFactory.BlackMaleHuman"),
    BlackFemaleHuman("com.china.designPattern.factory.humanFactory.BlackFemaleHuman");
    
    private String value = "";
    
    private HumanEnum(String value){
        this.value = value;
    } 
    
    public String getValue(){
        return this.value;
    } 
    
}
