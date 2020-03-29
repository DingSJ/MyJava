package com.china.java8.inAction;

import java.util.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
 * (2) 交易员都在哪些不同的城市工作过？
 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
 * (5) 有没有交易员是在米兰工作的？
 * (6) 打印生活在剑桥的交易员的所有交易额。
 * (7) 所有交易中，最高的交易额是多少？
 * (8) 找到交易额最小的交易。
 * */
public class TradeTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

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

        // (7) 所有交易中，总交易额是多少？
        testFun8(transactions);

    }

    private static void testFun8(List<Transaction> transactions) {
        Optional<Integer> reduce = transactions.stream().map(Transaction::getValue).reduce(Integer::sum);
        System.out.println(reduce.get().doubleValue());
    }

    private static void testFun7(List<Transaction> transactions) {
        Optional<Transaction> max = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue));
        System.out.println(max.get().getValue());

        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
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
                .sorted(Comparator.comparing(Trader::getName))
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
                .filter(transaction ->  2011 == transaction.getYear())
                .sorted(Comparator.comparing(Transaction::getValue)).collect(toList());
        System.out.println(collect);
    }


    static class Trader{
        private final String name;
        private final String city;
        public Trader(String n, String c){
            this.name = n;
            this.city = c;
        }
        public String getName(){
            return this.name;
        }
        public String getCity(){
            return this.city;
        }
        @Override
        public String toString(){
            return "Trader:"+this.name + " in " + this.city;
        }
    }

    static class Transaction{
        private final Trader trader;
        private final int year;
        private final int value;
        public Transaction(Trader trader, int year, int value){
            this.trader = trader;
            this.year = year;
            this.value = value;
        }
        public Trader getTrader(){
            return this.trader;
        }
        public int getYear(){
            return this.year;
        }
        public int getValue(){
            return this.value;
        }
        @Override
        public String toString(){
            return "{" + this.trader + ", " +
                    "year: "+this.year+", " +
                    "value:" + this.value +"}";
        }
    }
}
