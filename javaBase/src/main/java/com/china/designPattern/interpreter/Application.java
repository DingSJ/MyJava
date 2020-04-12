package com.china.designPattern.interpreter;

public class Application {

    public static void main(String[] args) {
        Context context = new Context();
        context.addValue("a", 6);
        context.addValue("b", 9);
        context.addValue("c", 1);

        Variable a = new Variable("a");
        Variable b = new Variable("b");
        Variable c = new Variable("c");

        AbstractExpression multiplyValue = new Multiply(a, b);
        AbstractExpression subtractValue = new Subtract(a, b);
        AbstractExpression addValue = new Add(subtractValue, c);
        AbstractExpression divisionValue = new Division(multiplyValue, addValue);

        System.out.println(context.getValueMap());
        System.out.printf("(a*b)/(a-b+c) = %d", divisionValue.interpreter(context));
    }
}