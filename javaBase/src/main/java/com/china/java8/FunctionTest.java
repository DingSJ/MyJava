package com.china.java8;

import org.junit.Test;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.ToDoubleFunction;

public class FunctionTest {

    @Test
    public void testFun() {
        Integer value = 123;
        int iVal = intFunction(value, val -> val + 100);
        System.out.println(iVal);
    }

    public int intFunction(int value, IntFunction<Integer> function) {
        int val = function.apply(value);
        return val;
    }

    // Double
    @Test
    public void testFun2() {
        Double value = 123.9D;
        Double iVal = doubleFunction(value, val -> val + 100);
        System.out.println(iVal);
    }
    public Double doubleFunction(double value, DoubleFunction<Double> function) {
        return function.apply(value);
    }

    // Double
    @Test
    public void testFun3() {
        String value = "123.9D";
        Double iVal = toDoubleFun(value, val -> Double.parseDouble(val));
        System.out.println(iVal);
    }
    public Double toDoubleFun(String value, ToDoubleFunction<String> function) {
        return function.applyAsDouble(value);
    }


}
