package com.china.designPattern.interpreter;

//抽象解释器
public abstract class AbstractExpression {

    public abstract int interpreter(Context context);
}