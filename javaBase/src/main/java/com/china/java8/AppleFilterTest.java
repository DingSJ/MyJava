package com.china.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 测试 Java7 中的过滤方式
 */
public class AppleFilterTest {

    public static void main(String[] args) {
        List<Apple> appleList = buildApples();
        // 找出绿色（green）的苹果
        testJava7(appleList);
        System.out.println("==========================================");

        testJava8(appleList);
        System.out.println("==========================================");

        testThread();
        testThread2();
        testThread3();
        testThread4();
        testThread5();
        testThread6();
        testThread7();
        testThread8();
        testThread9();

        System.out.println("Func Test ..... ");
        testFunction();
        testThread10();
        testListPredict();
        testListBiPredict();

        System.out.println("test Consumer....");
        testConsumer();

        System.out.println("sort list.....");
        testCompator();
    }

    private static void testCompator() {
        List<Apple> appleList = buildApples();
        Comparator<Apple> comparator = (o1, o2) -> o1.color.compareTo(o2.color);
        Comparator<Apple> compColor2 = (o1, o2) -> o1.weight - o2.weight;
        appleList.sort(comparator);
//        appleList.sort(Apple::compareTo);


        appleList.sort(Comparator.comparing(Apple::getColor));
        System.out.println(appleList);
    }

    private static void testConsumer() {
        Consumer<String> consumer = System.out::println;
//        Consumer<String> consumer = str -> System.out.println(str);
        userConsumer("Hello>>>", consumer);
    }

    private static<T> void userConsumer(T t, Consumer<T> consumer) {
        consumer.accept(t);
        consumer.accept(t);
        consumer.accept(t);
    }


    private static void testListBiPredict() {
        System.out.println("Bi predict...");
        List<Apple> appleList = buildApples();
        List<Apple> appWeightFilter = appleBiPredict(appleList, (a1,a2) -> a1.weight > a2.weight);
        System.out.println(appWeightFilter);
    }

    private static List<Apple> appleBiPredict(List<Apple> appleList, BiPredicate<Apple,Apple> biPredicate) {
        List<Apple> apples = new ArrayList<>();
        for (Apple app : apples) {
            if (biPredicate.test(app, new Apple("A", 60))) {
                apples.add(app);
            }
        }
        return apples;
    }

    private static void testListPredict() {
        List<Apple> appleList = buildApples();
        List<Apple> appWeightFilter= appleList.stream().filter(apple -> apple.weight >= 50).sorted(Apple::compareTo).collect(Collectors.toList());
        System.out.println(appWeightFilter);
    }

    private static void testThread10() {
        Runnable runnable = () -> System.out.println(".." + Thread.currentThread().getName());
        runnable.run();
        process(runnable);
        process(runnable);
        process(runnable);
    }

    private static void process(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void testFunction() {
        Comparator<Apple> compWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight - o2.weight;
            }
        };
        Comparator<Apple> compColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight - o2.weight;
            }
        };
        Comparator<Apple> compColor2 = (o1, o2) -> o1.weight - o2.weight;

        Function<String, Integer> fun = o1 -> o1.length();
        int length = fun.apply("asdfdsfsdf");
        System.out.println(length);

        int compare = compColor.compare(new Apple("AA", 100), new Apple("BB", 99));
        System.out.println("Compapre: " + compare);

        List<Apple> appleList = buildApples();
        Apple apple = appleList.stream().min(compColor).get();
        System.out.println("Apple: " + apple);
    }

    private static void testThread9() {

        System.out.printf("dsafas%d", 10);
        System.out.println("====");
    }

    private static void testThread8() {
        Thread thread = new Thread(() -> {
            Thread t = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + "执行开始");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " : " + "执行完了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.setName("My-Work-Thread");
            t.setDaemon(true);
            t.start();
        });
        thread.setDaemon(false);
        thread.start();
        try {
            thread.join(5_000);
//            thread.join();
        } catch (InterruptedException e) {
            System.out.println("超时了......");
            e.printStackTrace();
        }

        System.out.println("over");
    }

    // 表达式的值必须是 final 类型
    private static void testThread5() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> myFun(finalI), "MyThread - 5").start();
        }
    }

    private static void testThread6() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        for (Integer index : nums) {
            new Thread(() -> myFun(index), "MyThread - 5").start();
        }
    }

    private static void testThread7() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        for (Integer index : nums) {
            executor.submit(() -> myFun(index));
        }
        executor.shutdown();
    }

    private static void myFun(int i) {
        System.out.println(Thread.currentThread().getName() + " - " + i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testThread4() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "---------------");
        }, "MyThread-4").start();
    }

    private static void testThread() {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            int i = 1 / 0;
        }, "MyThread-1");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": Error, e:" + e);
            }
        });
        thread.start();
    }

    private static void testThread2() {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            int i = 1 / 0;
        }, "MyThread-2");
        thread.setUncaughtExceptionHandler((currThread, exception) -> System.out.println(currThread.getName() + ": Error, e:" + exception));
        thread.start();
    }

    private static void testThread3() {
        Thread thread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                int i = 1 / 0;
            } catch (Exception e) {
                System.out.println("catch error...");
            }
        }, "MyThread-3");
        // 线程中捕获异常，此处不再捕获
        thread.setUncaughtExceptionHandler((currThread, exception) -> System.out.println(currThread.getName() + ": Error, e:" + exception));
        thread.start();
    }

    /**
     * 以 Java8 的形式过滤苹果
     */
    private static void testJava8(List<Apple> appleList) {
        List<Apple> greenApps2 = filterGreenApples2(appleList, apple -> "green".equals(apple.getColor()));
        System.out.println("greenApps2 : " + greenApps2);


    }

    /**
     * 以 Java7 的形式过滤苹果
     */
    private static void testJava7(List<Apple> appleList) {
        List<Apple> greenApps = filterGreenApples(appleList);
        System.out.println("greenApps : " + greenApps);

        List<Apple> greenApps2 = filterGreenApples2(appleList, new ColorAppleFilter());
        System.out.println("greenApps2 : " + greenApps2);

        List<Apple> greenApps3 = filterGreenApples3(appleList, new ColorAppleFilter2("green"));
        System.out.println("greenApps3 : " + greenApps3);

        List<Apple> greenApps4 = filterGreenApples2(appleList, new ColorAppleFilter() {
            @Override
            public boolean test(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        System.out.println("greenApps4 : " + greenApps4);
    }


    //    @FunctionalInterface
    public interface AppleFilter {
        boolean test(Apple apple);
    }

    public static class ColorAppleFilter implements AppleFilter {

        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    public static class ColorAppleFilter2 implements AppleFilter {
        private String color;

        public ColorAppleFilter2(String color) {
            this.color = color;
        }

        @Override
        public boolean test(Apple apple) {
            return color.equals(apple.getColor());
        }
    }

    private static List<Apple> filterGreenApples(List<Apple> appleList) {
        List<Apple> apps = new ArrayList<>();
        for (Apple apple : appleList) {
            if ("green".equals(apple.getColor())) {
                apps.add(apple);
            }
        }
        return apps;
    }

    private static List<Apple> filterGreenApples2(List<Apple> appleList, AppleFilter filter) {
        List<Apple> apps = new ArrayList<>();
        for (Apple apple : appleList) {
            if (filter.test(apple)) {
                apps.add(apple);
            }
        }
        return apps;
    }

    private static List<Apple> filterGreenApples3(List<Apple> appleList, ColorAppleFilter2 filter) {
        List<Apple> apps = new ArrayList<>();
        for (Apple apple : appleList) {
            if (filter.test(apple)) {
                apps.add(apple);
            }
        }
        return apps;
    }

    private static List<Apple> buildApples() {
        List<Apple> apps = Arrays.asList(new Apple("white", 50),
                new Apple("red", 58),
                new Apple("green", 70),
                new Apple("yellow", 45),
                new Apple("grew", 55),
                new Apple("black", 66),
                new Apple("blue", 30));
        return apps;
    }


    static class Apple implements Comparable<Apple> {
        private String color;
        private int weight;

        public Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
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
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Apple o) {
            return this.weight - o.weight;
        }
    }
}
