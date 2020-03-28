package com.china.java8.inAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 策略模式
 * */
public class AppleFilterTest3 {

    public static void main(String[] args) {
        List<Apple> appleList = buildApples();

        // 策略模式
        List<Apple> appList = normalFilter(appleList, new WeightAppleFilter());
        System.out.println(appList);
        // 策略模式
        List<Apple> appList2 = normalFilter(appleList, new NameAppleFilter());
        System.out.println(appList2);

        // java 8
        List<Apple> appList3 = normalFilter(appleList, apple -> apple.getWeight() > 60);
        System.out.println(appList3);
        List<Apple> appList4 = normalFilter(appleList, apple -> apple.getName().contains("B"));
        System.out.println(appList4);

    }

    private static List<Apple> normalFilter(List<Apple> appleList, ApplePredicate predicate) {
        if (predicate == null) {
            return appleList;
        }
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if (predicate.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 策略模式接口
     * */
    interface ApplePredicate{
        boolean test(Apple apple);
    }
    /**
     * 策略模式实现 - 重量
     * */
    static class WeightAppleFilter implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 50;
        }
    }

    /**
     * 策略模式实现 - 名称
     * */
    static class NameAppleFilter implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getName().contains("A");
        }
    }


    private static List<Apple> buildApples() {
        List<Apple> apples = Arrays.asList(new Apple("A1", 55),
                new Apple("A2", 60),new Apple("A3", 62),
                new Apple("B1", 55),new Apple("B2", 70),
                new Apple("C1", 53),new Apple("C2", 45),
                new Apple("D1", 57),new Apple("D1", 60),
                new Apple("E1", 70),new Apple("E1", 58)
        );
        return apples;
    }

    static class Apple{
        private String name;
        private int weight;

        public Apple() {
        }

        public Apple(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
