package com.china.designPattern.interpreter;

public class Variable extends AbstractExpression {

    private final String key;

    public Variable(String key) {
        this.key = key;
    }

    @Override
    public int interpreter(Context context) {
        return context.getValue(key);
    }
}