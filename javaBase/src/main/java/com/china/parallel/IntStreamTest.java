package com.china.parallel;

import java.util.stream.IntStream;

public class IntStreamTest {
    public static void main(String[] args) {
//        IntStream.range(0, 10).forEach(x -> System.out.println(x));
        IntStream.rangeClosed(0, 10).forEach(x -> System.out.println(x));
    }
}
