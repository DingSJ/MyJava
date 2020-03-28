package com.china.java8.inAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数据多样化输出
 * */
public class ApplePrintTest {

    public static void main(String[] args) {
        List<Apple> appleList = buildApples();

        // 1. 策略模式
        List<String> appStr = print(appleList, new WeightAppleConsumer());
        System.out.println(appStr);

        // 2. 匿名内部类
        List<String> appStr1 = print(appleList, new WeightAppleConsumer(){
            @Override
            public String accept(Apple apple) {
                return apple.getWeight() > 50 ? apple.getWeight() + " - Weight" :apple.getWeight() + " - Light" ;
            }
        });
        System.out.println(appStr1);

        List<String> appStr2 = print(appleList, new NameAppleConsumer());
        System.out.println(appStr2);

        // 3. java 8 lambda 表达式
        List<String> appStr3 = print(appleList, apple -> apple.getName() + "-" + apple.getWeight());
        System.out.println(appStr3);

        List<String> appStr4 = print(appleList, Apple::getName);
        System.out.println(appStr4);

        /**
             [55, 60, 62, 55, 70, 53, 45, 57, 60, 70, 58]
             [A1, A2, A3, B1, B2, C1, C2, D1, D1, E1, E1]
             [A1-55, A2-60, A3-62, B1-55, B2-70, C1-53, C2-45, D1-57, D1-60, E1-70, E1-58]

         * */

    }

    private static List<String> print(List<Apple> appleList, AppleConsumer consumer) {
        List<String> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            apples.add(consumer.accept(apple));
        }
        return apples;
    }

    /**
     * 策略模式接口
     * */
    interface AppleConsumer{
        String accept(Apple apple);
    }
    /**
     * 策略模式实现 - 重量
     * */
    static class WeightAppleConsumer implements AppleConsumer {
        @Override
        public String accept(Apple apple) {
            return String.valueOf(apple.getWeight());
        }
    }

    /**
     * 策略模式实现 - 名称
     * */
    static class NameAppleConsumer implements AppleConsumer {
        @Override
        public String accept(Apple apple) {
            return apple.getName();
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
