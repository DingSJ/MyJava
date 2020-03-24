package com.china.java8;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试 Java8 Stream
 * @author dingsj
 */
public class FilterTest {
    public static void main(String[] args) {

        // 数据初始化
        List<Apple> appleList = getAllApple();

        // 过滤颜色
        testColorFilter(appleList);
        // 过滤颜色 - 函数方式
        testColorFilter2(appleList);
        // peek 遍历
        testColorFilter3(appleList);
        testColorFilter4(appleList);

        // 过滤数量
        testAmtFilter5(appleList);
        testAmtFilter6(appleList);

        // 过滤数量
        testAmtSum(appleList);
        testAmtSum2(appleList);
        testAmtSum3(appleList);
        testAmtSum4(appleList);
    }

    private static void testColorFilter(List<Apple> appleList) {
        System.out.println("过滤颜色1.............");
        List<Apple> apples = appleList.stream().filter(apple -> "Green".equals(apple.color)).collect(Collectors.toList());
        System.out.println(apples);
    }
    private static void testColorFilter2(List<Apple> appleList) {
        System.out.println("过滤颜色2.............");
        List<Apple> apples = appleList.stream().filter(apple -> testGreen(apple)).collect(Collectors.toList());
        System.out.println(apples);
    }
    private static void testColorFilter3(List<Apple> appleList) {
        System.out.println("Peek 遍历 -1.............");
        List<Apple> apples = appleList.stream().peek(apple -> System.out.println(apple)).collect(Collectors.toList());
//        System.out.println(apples);
    }
    private static void testColorFilter4(List<Apple> appleList) {
        System.out.println("Peek 遍历 -2.............");
        appleList.stream().peek(System.out::println).collect(Collectors.toList());
//        System.out.println(apples);
    }

    private static void testAmtFilter5(List<Apple> appleList) {
        System.out.println("过滤数量.............");
        appleList.stream().filter(apple -> apple.amt <= 100).peek(System.out::println).collect(Collectors.toList());
    }
    private static void testAmtFilter6(List<Apple> appleList) {
        System.out.println("过滤数量 - 输出name.............");
        appleList.stream().filter(apple -> apple.amt <= 100).map(apple -> apple.getName()).peek(System.out::println).collect(Collectors.toList());
    }
    private static void testAmtSum(List<Apple> appleList) {
        System.out.println("数量求和 - 1 .............");
        AtomicInteger sum = new AtomicInteger();
        appleList.stream().filter(apple -> apple.amt <= 100).forEach(apple -> sum.getAndAdd(apple.amt));
        System.out.println("数量小等于100的总数量" + sum.get());
    }
    private static void testAmtSum3(List<Apple> appleList) {
        System.out.println("数量求和 - 3 .............");
        AtomicInteger sum = new AtomicInteger();
        appleList.stream().filter(apple -> apple.amt <= 100).map(apple -> apple.getAmt()).peek(System.out::println).forEach(a->sum.getAndAdd(a));
        System.out.println("数量小等于100的总数量: " + sum.get());
    }
    private static void testAmtSum4(List<Apple> appleList) {
        System.out.println("数量求和 - 4 .............");
        int total = appleList.stream().filter(apple -> apple.amt <= 100).map(apple -> apple.getAmt()).peek(System.out::println).reduce(0, (aTotal,ele)-> aTotal+ele);
        System.out.println("数量小等于100的总数量: " + total);
    }
    private static void testAmtSum2(List<Apple> appleList) {
        System.out.println("数量求和 - 2 .............");
        long count = appleList.stream().filter(apple -> apple.amt <= 100).count();
        System.out.println("数量小等于100的类型总计" + count);
    }

    private static boolean testGreen(Apple apple){
        return "Green".equals(apple.color);
    }

    private static List<Apple> getAllApple() {
        List<Apple> appleList = Stream.of(new Apple("Apple-BeiJing", "Red",100),
                new Apple("Apple-ShanXi", "Black",120),
                new Apple("Apple-ShanDong", "Green",90),
                new Apple("Apple-BeiJing", "Red",190),
                new Apple("Apple-GuangZhou", "Yellow",170),
                new Apple("Apple-HaiNan", "Grew",150),
                new Apple("Apple-HeNan", "White",70)
                ).collect(Collectors.toList());
        return appleList;
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
