package com.china.java8;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1 测试集合的流式操作
 * 2 对比集合Iterator 和 Stream 的处理速度对比
 * */
public class CountTest {
    public static void main(String[] args) {
//        testCount();
//        testCountWithItartorAndStream();

//        testStreamMap();

//        testMaxAndMin();
        
        testStreamReduce();
    }

    private static void testStreamReduce() {
        List<Student> students = Stream.of(new Student("AAA",100),
                new Student("BBB",99), new Student("CCC",105),
                new Student("DDD",100), new Student("EEE",102)
        ).collect(Collectors.toList());

        int count = Stream.of(1, 2, 3, 4).reduce(0, (a, b) -> a + b);

        // 对象中属性计算
        int count1 = students.stream().reduce(new Student("cc",0), (a,b)->new Student("",a.score + b.score)).getScore();

        // 条件过滤
        int count2 = students.stream().filter(student -> student.getScore() > 100).reduce(new Student("cc",0), (a,b)-> studentScoreAdd(a,b)).getScore();

        // 对象转换再计算
        /**
         * sum 为上次计算的结果
         * element 为本次迭代的元素
         * */
        int count3 = students.stream().map(student -> student.getScore()).reduce(0, (sum,element)-> sum + element);

        System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);
    }


    // 找最大最小值
    private static void testMaxAndMin() {
        List<Student> students = Stream.of(new Student("AAA",100),
                new Student("BBB",99), new Student("CCC",105),
                new Student("DDD",100), new Student("EEE",102)
                ).collect(Collectors.toList());
        System.out.println("students: " + students);

        Student studentMin = students.stream().min(Student::compareTo).get();
        Student studentMax = students.stream().max(Student::compareTo).get();
        Student stdMin = students.stream().min(Comparator.comparingInt(o -> o.score)).get();
        Optional<Student> optionalStudent = students.stream().findFirst();
        System.out.println("optionalStudent: " + JSON.toJSONString(optionalStudent));
        System.out.println("studentMax :" + studentMax);
        System.out.println("studentMin : " + studentMin);
        System.out.println("stdMin : " + stdMin);
    }

    private static Student studentScoreAdd(Student a, Student b) {
        return new Student("",a.score+b.score);
    }

    static class Student implements Comparable<Student> {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public int compareTo(Student student) {
            return this.score - student.score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

    private static void testStreamMap() {
        // 去重
        List<String> dataList = Stream.of("aa", "bb", "cc","cc", "aa", "dd").distinct().collect(Collectors.toList());
        System.out.println(dataList);

        // 转化大小写
        List<String> dataList2 = Stream.of("aa", "bb", "cc","cc", "aa", "dd").distinct()
                .map(value -> value.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(dataList2);

        // 计算不重复数量
        long count = Stream.of("a", "b", "c", "d", "a", "b").distinct().count();
        System.out.println(count);

        // 遍历输出
        dataList.stream().distinct().forEach(System.out::println);
        System.out.println("===============");
        dataList.stream().distinct().forEach(value -> System.out.println(value));

        // 合并集合
        List<Integer> together = Stream.of(asList(1, 2), asList(1, 2, 3, 4)).flatMap(val -> val.stream()).distinct().collect(Collectors.toList());
        System.out.println("together: " + together);
    }

    private static List<Integer> asList(int...i) {
        List<Integer> data = new ArrayList<>();
        for (int is : i) {
            data.add(is);
        }
        return data;
    }

    /**
     * 100 个元素
     * Iterator-Filter, times: 0, iTotal: 14
     * Stream-Filter, times: 10, Total: 14
     *
     * 500 个元素
     * Iterator-Filter, times: 0, iTotal: 52
     * Stream-Filter, times: 11, Total: 52
     *
     * 5000 个元素
     * Iterator-Filter, times: 3, iTotal: 543
     * Stream-Filter, times: 14, Total: 543
     *
     * 50000 个元素
     * Iterator-Filter, times: 19, iTotal: 5447
     * Stream-Filter, times: 47, Total: 5447
     *
     * 500000 个元素
     * Stream-Filter, times: 40, Total: 54809
     * Iterator-Filter, times: 42, iTotal: 54809
     *
     * 5000000 个元素
     * Iterator-Filter, times: 122, iTotal: 549701
     * Stream-Filter, times: 123, Total: 549701
     *
     * 7000000 个元素
     * Iterator-Filter, times: 137, iTotal: 770772
     * Stream-Filter, times: 145, Total: 770772
     * */
    private static void testCountWithItartorAndStream() {
        List<String> values = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        CountDownLatch downLatch = new CountDownLatch(1);
        new Thread(()->{
            System.out.println("save data start ....");
            long saveStart = System.currentTimeMillis();
            IntStream.range(1, 7000000).forEach((i -> values.add("adsf-" + random.nextInt(100))));
            long saveEnd = System.currentTimeMillis();
            System.out.println("SaveData times: " + (saveEnd - saveStart));
            downLatch.countDown();
        }).start();
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stream
        new Thread(()->{
            System.out.println("Stream count start ....");
            long stranmStart = System.currentTimeMillis();
            long total = values.stream().filter(value -> value.contains("-2")).count();
            long stranmEnd = System.currentTimeMillis();
            System.out.println("Stream-Filter, times: " + (stranmEnd - stranmStart) + ", Total: " +total);
        }).start();

        // Iterator
        new Thread(()->{
            System.out.println("Iterator count start ....");
            long iteratorStart = System.currentTimeMillis();
            long itotal = 0;
            for (String value : values) {
                if (value.contains("-2")) {
                    itotal++;
                }
            }
            long iteratorEnd = System.currentTimeMillis();
            System.out.println("Iterator-Filter, times: " + (iteratorEnd - iteratorStart) + ", iTotal: " + itotal);
        }).start();

    }

    private static void testCount() {

        List<String> values = new ArrayList<>();
        values.add("aa-1");
        values.add("aa-2");
        values.add("aa-3");
        values.add("bb-1");
        values.add("bb-2");
        values.add("bb-3");
        values.add("bb-4");
        values.add("cc-1");
        values.add("cc-2");

        Random random = new Random(System.currentTimeMillis());
        IntStream.range(1, 10000).forEach((i -> {
            values.add("adsf-" + random.nextInt(100));
            System.out.println(Thread.currentThread().getName());
        }));

        long total = values.stream().filter(value -> value.contains("-2")).count();
        System.out.println("total aa and cc: " + total);
    }
}
