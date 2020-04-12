package com.china.designPattern.interpreter;

public class Division extends AbstractExpression {

    private final AbstractExpression left;

    private final AbstractExpression right;

    public Division(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpreter(Context context) {
        int right = this.right.interpreter(context);
        if (right != 0) {
            return left.interpreter(context) / right;
        }

        return -1;
    }
}