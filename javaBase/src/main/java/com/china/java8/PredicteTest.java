package com.china.java8;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试 Java8操作
 *      Predict
 *      BiPredict
 *      IntPredict
 *      LongPredict
 *      DoublePredict
 *
 *      mqtest()
 *      and()
 *      or()
 *      negate()
 *
 * @author dingsj
 * */
public class PredicteTest {

    private static List<Apple> appleList;

    @Before
    public void before(){
        appleList = Stream.of(new Apple("Apple-BeiJing", "Red",100),
                new Apple("Apple-ShanXi", "Black",120),
                new Apple("Apple-ShanDong", "Green",90),
                new Apple("Apple-BeiJing", "Red",190),
                new Apple("Apple-GuangZhou", "Yellow",170),
                new Apple("Apple-HaiNan", "Grew",150),
                new Apple("Apple-HeNan", "White",70)
        ).collect(Collectors.toList());
    }

    /**
     * {@link Predicate<>#mqtest()} 用于校验或者过滤数据，单个参数
     * */
    @Test
    public void testFilter(){
        List<Apple> apples = filterApples(appleList,apple -> apple.amt < 100);
        System.out.println(apples);
    }
    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> apps = new ArrayList<>();
        for (Apple app : appleList) {
            if (predicate.test(app)) {
                apps.add(app);
            }
        }
        return apps;
    }
    /**
     * {@link java.util.function.BiPredicate<>#mqtest()} 用于校验或者过滤数据，两个参数
     * */
    @Test
    public void testFilter2(){
        List<Apple> apples = filterApples2(appleList, (color,amt) -> "Green".equals(color) && amt < 100);
        System.out.println(apples);
    }

    public static List<Apple> filterApples2(List<Apple> apples, BiPredicate<String, Integer> predicate) {
        List<Apple> apps = new ArrayList<>();
        for (Apple app : apples) {
            if (predicate.test(app.color,app.amt)) {
                apps.add(app);
            }
        }
        return apps;
    }

    /**
     * {@link IntPredicate<>#mqtest()} 用于校验或者过滤数据，一个整型参数
     * */
    @Test
    public void testIntPredicted(){
        List<Apple> apples = funIntPredicted(appleList, amt -> amt < 100);
        System.out.println(apples);
    }
    /**
     * {@link IntPredicate<>#mqtest()} 用于校验或者过滤数据，一个整型参数
     * */
    @Test
    public void testIntPredicted2(){
        List<Apple> apples = funIntPredictedNegate(appleList, amt -> amt < 100);
        System.out.println(apples);
    }
    /**
     * {@link IntPredicate<>#mqtest()} 用于校验或者过滤数据，一个整型参数
     * */
    @Test
    public void testIntPredicted3(){
        funIntPredictedNegate(appleList, amt -> amt < 100).stream().map(apple -> apple.getName() + " : " + apple.amt).forEach(value -> System.out.println(value));
    }
    // 处理数据输出
    @Test
    public void testIntPredicted4(){
        funIntPredictedNegate(appleList, amt -> amt < 100).stream().map(apple -> apple.getName() + " : " + apple.amt).forEach(System.out::println);
    }
    // 测试 negate 操作
    @Test
    public void testIntPredicted5(){
        funIntPredictedNegate(appleList, amt -> amt > 100).stream().map(apple -> apple.getName() + " : " + apple.amt).peek(System.out::println).collect(Collectors.toList());
    }

    // 测试 and 操作
    @Test
    public void testIntPredicted6(){
        funIntPredictedAnd(appleList, amt -> amt > 90).stream().map(apple -> apple.getName() + " : " + apple.amt).forEach(System.out::println);
    }

    // 测试 or 操作
    @Test
    public void testIntPredicted7(){
        funIntPredictedOr(appleList, amt -> amt == 90).stream().map(apple -> apple.getName() + " : " + apple.amt).forEach(System.out::println);
    }


    public List<Apple> funIntPredicted(List<Apple> appleList, IntPredicate predicate) {
        List<Apple> apps = new ArrayList<>();
        for (Apple app : appleList) {
            if (predicate.test(app.amt)) {
                apps.add(app);
            }
        }
        return apps;
    }
    // 取反操作
    public List<Apple> funIntPredictedNegate(List<Apple> appleList, IntPredicate predicate) {
        List<Apple> apps = new ArrayList<>();
        for (Apple app : appleList) {
            if (predicate.negate().test(app.amt)) {
                apps.add(app);
            }
        }
        return apps;
    }

    // and
    public List<Apple> funIntPredictedAnd(List<Apple> appleList, IntPredicate predicate) {
        List<Apple> apps = new ArrayList<>();
        for (Apple app : appleList) {
            if (predicate.and(value -> value == 100).test(app.amt)) {
                apps.add(app);
            }
        }
        return apps;
    }

    // and
    public List<Apple> funIntPredictedOr(List<Apple> appleList, IntPredicate predicate) {
        List<Apple> apps = new ArrayList<>();
        for (Apple app : appleList) {
            if (predicate.or(value -> value == 100).test(app.amt)) {
                apps.add(app);
            }
        }
        return apps;
    }


    static class Apple implements Comparable<Apple> {
        private String name;
        private String color;
        private int amt;

        public Apple(String name, String color, int amt) {
            this.name = name;
            this.color = color;
            this.amt = amt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getAmt() {
            return amt;
        }

        public void setAmt(int amt) {
            this.amt = amt;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    ", amt='" + amt + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Apple apple) {
            return this.amt - apple.amt;
        }
    }
}
