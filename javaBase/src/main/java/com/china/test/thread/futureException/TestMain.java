package com.china.test.thread.futureException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestMain {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Mythread());
        }

//        FutureTask<String> futureTask = new FutureTask<String>(new Mythread());
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threads.size(); i++) {
            executorService.submit(threads.get(i));
        }
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("主线程执行完毕");
    }
}
