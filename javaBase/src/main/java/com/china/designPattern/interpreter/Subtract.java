package com.china.designPattern.interpreter;

public class Subtract extends AbstractExpression {

    private final AbstractExpression left;

    private final AbstractExpression right;

    public Subtract(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpreter(Context context) {
        return left.interpreter(context) - right.interpreter(context);
    }
}