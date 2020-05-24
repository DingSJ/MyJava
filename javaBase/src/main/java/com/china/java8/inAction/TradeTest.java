package com.china.java8.inAction;

import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
 * (2) 交易员都在哪些不同的城市工作过？
 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
 * (5) 有没有交易员是在米兰工作的？
 * (6) 打印生活在剑桥的交易员的所有交易额。
 * (7) 所有交易中，最高的交易额是多少？
 * (8) 找到交易额最小的交易。
 */
public class TradeTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // (0) 数据过滤
        testFun0(transactions);

        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        testFun1(transactions);

        // (2) 交易员都在哪些不同的城市工作过？
        testFun2(transactions);

        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        testFun3(transactions);

        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        testFun4(transactions);

        // (5) 有没有交易员是在米兰工作的？
        testFun5(transactions);

        // (6) 打印生活在剑桥的交易员的所有交易额。
        testFun6(transactions);

        // (7) 所有交易中，最高的交易额是多少？
        // (8) 找到交易额最小的交易。
        testFun7(transactions);

        // (8) 所有交易中，总交易额是多少？
        testFun8(transactions);

        // （9）分组
        testFun9(transactions);

        // （10） 求和
        testFun10(transactions);

        // （11） 求和
        testFun11(transactions);

        // （12） 分組
        testFun12(transactions);
        // （13） 分組
        testFun13(transactions);


        // （14） 分区
        // partitioningBy(Predicate<T>))
        testFun14(transactions);

        // mqtest sum
        testFun15();

    }

    private static void testFun0(List<Transaction> transactions) {
        System.out.println("transactions : " + transactions);
        List<Transaction> collect = transactions.stream().filter(transaction -> transaction.getYear() == 2011).collect(toList());
        System.out.println("collect : " + collect);

    }

    private static void testFun15() {
        final long LIMIT = 10_000_000;

        long s0 = System.nanoTime();
        long reduce0 = Stream.iterate(1L, i -> i + 1).limit(LIMIT).reduce(0L, (sum, i) -> sum + i);
        long e0 = System.nanoTime();
        System.out.printf("处理用时：%d, 结果：%d\n",(e0-s0)/1000, reduce0);

        long s1 = System.nanoTime();
        long reduce = Stream.iterate(1L, i -> i + 1).limit(LIMIT).mapToLong(Long::longValue).reduce(0L, (sum, i) -> sum + i);
        long e1 = System.nanoTime();
        System.out.printf("处理用时：%d, 结果：%d\n",(e1-s1)/1000, reduce);

        long s2 = System.nanoTime();
        long reduce2 = LongStream.rangeClosed(0L, LIMIT).sum();
        long e2 = System.nanoTime();
        System.out.printf("处理用时：%d, 结果：%d\n",(e2-s2)/1000, reduce2);

        long s3 = System.nanoTime();
        long reduce3 = LongStream.rangeClosed(0L, LIMIT).reduce(0L,(sum, i) -> sum + i);
        long e3 = System.nanoTime();
        System.out.printf("处理用时：%d, 结果：%d\n",(e3-s3)/1000, reduce3);


        long s4 = System.nanoTime();
        long reduce4 = LongStream.rangeClosed(0L, LIMIT).parallel().reduce(0L,(sum, i) -> sum + i);
        long e4 = System.nanoTime();
        System.out.printf("处理用时：%d, 结果：%d\n",(e4-s4)/1000, reduce4);

        long s5 = System.nanoTime();
        long reduce5 = 0L;
        for (int i = 0; i <= LIMIT ; i++) {
            reduce5 += i;
        }
        long e5 = System.nanoTime();
        System.out.printf("处理用时：%d, 结果：%d\n",(e5-s5)/1000, reduce5);


//        处理用时：489122, 结果：50000005000000
//        处理用时：356304, 结果：50000005000000
//        处理用时：42059, 结果：50000005000000
//        处理用时：128822, 结果：50000005000000
//        处理用时：40103, 结果：50000005000000
//        处理用时：34332, 结果：50000005000000
    /*
        流的数据源和可分解性
                    源          可分解性

             ArrayList           极佳
            LinkedList            差
       IntStream.range           极佳
        Stream.iterate            差
               HashSet            好
               TreeSet            好
     */

    }

    private static void testFun14(List<Transaction> transactions) {
        Map<Boolean, Map<Trader, List<Transaction>>> collect = transactions.stream()
                .collect(partitioningBy(transaction -> transaction.getYear() == 2011, groupingBy(Transaction::getTrader)));
        System.out.println(collect);
    }

    private static void testFun13(List<Transaction> transactions) {
        transactions.stream()
                .collect(groupingBy(Transaction::getYear));
    }

    private static void testFun12(List<Transaction> transactions) {
        Map<Integer, Long> collect = transactions.stream()
                .collect(groupingBy(Transaction::getYear, counting()));
        System.out.println(collect);

    }

    private static void testFun11(List<Transaction> transactions) {
        int sum = transactions.stream()
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println("sum : " + sum);

        OptionalDouble average = transactions.stream()
                .mapToInt(Transaction::getValue)
                .average();
        System.out.println("average : " + average.getAsDouble());

        OptionalInt min = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min();
        System.out.println("min : " + min.getAsInt());

        OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println("max : " + max.getAsInt());
    }

    /**
     * collect(summarizingInt(Transaction::getValue))
     */
    private static void testFun10(List<Transaction> transactions) {
        IntSummaryStatistics collect = transactions.stream()
                .collect(summarizingInt(Transaction::getValue));
        System.out.println("collect:" + collect);
        System.out.println("collect-getSum:" + collect.getSum());
        System.out.println("collect-getAverage:" + collect.getAverage());
        System.out.println("collect-getCount:" + collect.getCount());
        System.out.println("collect-getMax:" + collect.getMax());
        System.out.println("collect-getMin:" + collect.getMin());

    }

    private static void testFun9(List<Transaction> transactions) {
        Map<Integer, List<Transaction>> collect = transactions.stream().
                collect(groupingBy(Transaction::getYear));
        System.out.println(collect);

        Map<String, List<Transaction>> collect1 = transactions.stream().
                collect(groupingBy(transaction -> transaction.getYear() + "年"));
        System.out.println(collect1);
    }

    private static void testFun8(List<Transaction> transactions) {
        Optional<Integer> reduce = transactions.stream().map(Transaction::getValue).reduce(Integer::sum);
        System.out.println(reduce.get().doubleValue());
    }

    private static void testFun7(List<Transaction> transactions) {
        Optional<Transaction> max = transactions.stream()
                .max(comparing(Transaction::getValue));
        System.out.println(max.get().getValue());

        transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println(max.get().getValue());

        OptionalInt maxCalories = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println(maxCalories.getAsInt());
    }

    private static void testFun6(List<Transaction> transactions) {
        transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .forEach(transaction -> System.out.println("Cambridge Trade Value: " + transaction.getValue()));
    }

    private static void testFun5(List<Transaction> transactions) {
        boolean b = transactions.stream().anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println(b);
    }

    private static void testFun4(List<Transaction> transactions) {
        List<String> collect = transactions.stream().map(transaction -> transaction.getTrader().getName()).sorted().collect(toList());
        System.out.println(collect);

        String collect1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .collect(joining("+"));
        System.out.println("String - joining: " + collect1);
    }

    private static void testFun3(List<Transaction> transactions) {
        List<Trader> collect = transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getTrader)
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(collect);
    }

    private static void testFun2(List<Transaction> transactions) {
        List<String> collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(collect);
    }

    private static void testFun1(List<Transaction> transactions) {
        List<Transaction> collect = transactions.stream()
                .filter(transaction -> 2011 == transaction.getYear())
                .sorted(comparing(Transaction::getValue)).collect(toList());
        System.out.println(collect);
    }


    static class Trader {
        private final String name;
        private final String city;

        public Trader(String n, String c) {
            this.name = n;
            this.city = c;
        }

        public String getName() {
            return this.name;
        }

        public String getCity() {
            return this.city;
        }

        @Override
        public String toString() {
            return "Trader:" + this.name + " in " + this.city;
        }
    }

    static class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return this.trader;
        }

        public int getYear() {
            return this.year;
        }

        public int getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return "{" + this.trader + ", " +
                    "year: " + this.year + ", " +
                    "value:" + this.value + "}";
        }
    }
}
