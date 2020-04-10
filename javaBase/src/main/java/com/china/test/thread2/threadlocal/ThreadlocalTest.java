package com.china.test.thread2.threadlocal;

import java.util.stream.IntStream;

public class ThreadlocalTest {

    public static void main(String[] args) {
        IntStream.range(1, 5).forEach((i)->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Context context = SystemContext.getSystemContext().getContext();
                    context.setValue(String.valueOf(i));
                    System.out.println(context.toString());
                }
            }, "线程-" + i ).start();
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Context context = SystemContext.getSystemContext().getContext();
                context.setValue("XXXXXXXXXXXXXX");
                System.out.println(context.toString());
            }
        }).start();

        SystemContext.getSystemContext().getContext().setValue("StaticMain");

        System.out.println("StaticMain: " + SystemContext.getSystemContext().getContext());
    }
}
