package com.china.jvm;

public class StringInternTest {
    public static void main(String[] args) {
        String str = "AAABBB";
        String str1 = new StringBuilder("AAA").append("BBB").toString();
        System.out.println(str1.intern() == str1);

        String str0 = "java";
        String str2 = new StringBuilder("ja").append("va").toString();
        String str22 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        System.out.println(str0 == str2);
        System.out.println(str22 == str2);

    }
}
