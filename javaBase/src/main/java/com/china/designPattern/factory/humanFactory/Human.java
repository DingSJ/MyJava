package com.china.designPattern.factory.humanFactory;

public interface Human {
    // 首先定义什么是人类

    // 人会笑，表达愉快
    void laugh();

    // 人会哭，表达悲伤
    void cry();

    // 人会说话
    void talk();

    // 定义性别
    void sex();
}
