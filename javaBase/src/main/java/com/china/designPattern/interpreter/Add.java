package com.china.designPattern.interpreter;

public class Add extends AbstractExpression {

    private final AbstractExpression left;

    private final AbstractExpression right;

    public AbstractExpression getLeft() {
        return left;
    }

    public AbstractExpression getRight() {
        return right;
    }

    public Add(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpreter(Context context) {
        return left.interpreter(context) + right.interpreter(context);
    }
}