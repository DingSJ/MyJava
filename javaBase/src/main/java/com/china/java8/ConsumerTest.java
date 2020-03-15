package com.china.java8;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试 Java8操作 :
 *              Consumer
 *              BiConsumer
 *              IntConsumer
 *              LongConsumer
 *              DoubleConsumer
 *              ObjIntConsumer
 *              ObjLongConsumer
 *              ObjDoubleConsumer
 *
 *             #accept(consumer)
 *             #andThen(consumer)
 *
 * @author dingsj
 * */
public class ConsumerTest {

    private static List<Apple> appleList;

    private static void accept(Apple apple) {
    }

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

    // 遍历水果品种，每种添加10个
    @Test
    public void testConsumer(){
        appleList.stream().forEach(apple -> apple.setAmt(apple.getAmt() + 10));
        System.out.println(appleList);
    }
    // 遍历水果品种，每种添加10个, 在加5个
    @Test
    public void testConsumer2(){
        appleList.stream().forEach(((Consumer<Apple>) apple -> apple.setAmt(apple.getAmt() + 10))
                .andThen(apple -> apple.setAmt(apple.amt + 5)));
        System.out.println(appleList);
    }
    // 遍历水果品种，每种添加10个, 在加5个,找出数量小于100的苹果品种
    @Test
    public void testConsumer3(){

        appleList.stream().forEach(((Consumer<Apple>) apple -> apple.setAmt(apple.getAmt() + 10))
                .andThen(apple -> apple.setAmt(apple.amt + 5)));

        List<Apple> apples = appleList.stream()
                .filter(((Predicate<Apple>) apple -> apple.getAmt() < 100)
                        .and(apple -> "White".equals(apple.getColor())))
                .collect(Collectors.toList());

        System.out.println(apples);
    }

    @Test
    public void testBiConsumer4(){
        List<Apple> apples = handleApps(appleList, (name,amt) -> {
            if ("Apple-HeNan".equals(name)) {
                System.out.println(name + " : " + amt);
            }
        });
        System.out.println(apples);
    }

    public List<Apple> handleApps(List<Apple> appleList, BiConsumer<String, Integer> biConsumer) {
        List<Apple> apps = new ArrayList<>();
        for (Apple app : appleList) {
            biConsumer.accept(app.getName(), app.getAmt());
            apps.add(app);
        }
        return apps;
    }

    // 使用 ObjIntConsumer
    @Test
    public void testBiConsumer5(){
        List<Apple> apples = handleApps2(appleList, (apple, value)->{
            if ("Apple-HeNan".equals(apple.getName())) {
                apple.setAmt(apple.getAmt() + value);
                System.out.println("============= Handle apple : " + apple);
            }
        });
        System.out.println(apples);
    }

    public List<Apple> handleApps2(List<Apple> appleList, ObjIntConsumer<Apple> objIntConsumer) {
        List<Apple> apps = new ArrayList<>();
        for (Apple app : appleList) {
            objIntConsumer.accept(app, 100);
            apps.add(app);
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
