package com.china.java8.inAction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 排序的几种写法
 * */
public class SortTest {
    public static void main(String[] args) {

        List<Apple> appleList = buildApples();

        // 1
        Comparator<Apple> c1 = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight - o2.weight;
            }
        };

        // 2
        Comparator<Apple> c2 = (Apple o1, Apple o2) -> o1.weight - o2.weight;

        // 3
        Comparator<Apple> c3 = (o1, o2) -> o1.weight - o2.weight;

        // 4
        Comparator<Apple> c4 = Comparator.comparingInt(o -> o.weight);

        // 5
        Comparator<Apple> c5 = Comparator.comparing(Apple::getWeight);

        appleList.sort(c5);
        System.out.println(appleList);
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
