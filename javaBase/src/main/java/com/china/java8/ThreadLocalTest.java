package com.china.java8;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ThreadLocalTest {
    public static void main(String[] args) {

        ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));
        System.out.println(formatter.get().getFormat().format(new Date()));

        ThreadLocal<HashMap<String, String>> hashMapThreadLocal = ThreadLocal.withInitial(() -> new HashMap<String, String>(16));
        hashMapThreadLocal.get().put("aaa", "bbb");

        Thread t = new Thread(()->{
            hashMapThreadLocal.get().put("aaa","ccccccccc");
            System.out.println("value : " + hashMapThreadLocal.get().get("aaa"));
        });
        t.start();

        String aaa = hashMapThreadLocal.get().get("aaa");
        System.out.println("aaa: " + aaa);

        test();
    }

    private static void test() {
        Runnable run = () -> System.out.println(Thread.currentThread().getName() + ": qAaaa");
        Thread t = new Thread(run);
        t.start();
    }
}
