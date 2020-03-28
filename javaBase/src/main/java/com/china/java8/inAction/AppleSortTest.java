package com.china.java8.inAction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * java 8 与 java 7 排序方式的比较
 * @author dingsj
 * */
public class AppleSortTest {
    public static void main(String[] args) {
        List<Apple> appleList = buildApples();

//        java7Sort(appleList);
//        java7Sort2(appleList);


//        java8Sort(appleList);
//        java8Sort2(appleList);
//        java8Sort3(appleList);
//        java8Sort4(appleList);
//        java8Sort5(appleList);


        parallelFilter(appleList);


    }

    /**
     * 并行过滤
     *      1, 3, 4  |   3, 5, 6
     *      core-1   |    core-2
     *    --------  result  -----
     *         4, 5, 6
     * */
    private static void parallelFilter(List<Apple> appleList) {
        List<Apple> collect = appleList.parallelStream().filter(apple -> {
            System.out.println(Thread.currentThread().getName() + " - Apple: " + apple.toString());
            return apple.weight > 50;
        }).collect(toList());
        System.out.println(collect);
    }

    // 数据分组
    private static void java8Sort5(List<Apple> appleList) {
        Map<String, List<Apple>> collect = appleList.stream().collect(Collectors.groupingBy(apple -> apple.getWeight() + "-Apple"));
        System.out.println(collect);
        System.out.println(collect.get("70-Apple"));
    }

    // 数据分组
    private static void java8Sort4(List<Apple> appleList) {
        Map<Integer, List<Apple>> collect = appleList.stream().collect(Collectors.groupingBy(Apple::getWeight));
        System.out.println(collect.get(70));
    }

    // 数据处理 + 过滤 + 排序
    private static void java8Sort3(List<Apple> appleList) {
        List<Apple> collect = appleList.stream().map(apple -> {
            if (apple.weight < 50) {
                apple.setWeight(apple.weight + 100);
            }
            return apple;
        }).filter(apple -> apple.weight > 100).sorted().collect(toList());

        System.out.println(collect);
    }

    // 条件过滤
    private static void java8Sort2(List<Apple> appleList) {
        List<Apple> collect = appleList.stream().filter(apple -> apple.weight >= 50).collect(toList());
        System.out.println(collect);
    }

    private static void java8Sort(List<Apple> appleList) {
        appleList.sort(comparing(Apple::getWeight));
        System.out.println(appleList);
    }

    private static void java7Sort2(List<Apple> appleList) {
        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight - o2.weight;
            }
        });
        System.out.println(appleList);
    }

    private static void java7Sort(List<Apple> appleList) {
        Collections.sort(appleList, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight - o2.weight;
            }
        });
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

    static class Apple implements Comparator<Apple> {
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

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.weight - o2.weight;
        }
    }
}
