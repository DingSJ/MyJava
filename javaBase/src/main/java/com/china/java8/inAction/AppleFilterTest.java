package com.china.java8.inAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 单一的过滤方式，不能适应客户的快速变化的需求
 * */
public class AppleFilterTest {

    public static void main(String[] args) {
        List<Apple> appleList = buildApples();

        List<Apple> appList = normalFilter(appleList, 50);
        System.out.println(appList);

    }

    private static List<Apple> normalFilter(List<Apple> appleList, int weight) {
        if (weight <= 0) {
            return appleList;
        }
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if (weight == apple.weight) {
                apples.add(apple);
            }
        }
        return apples;
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
