package com.china.test.thread.futureException;

import com.china.test.thread.statistics.ThreadResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 有返回值的多线程任务
 * */
public class TestCallBackMain {
    public static void main(String[] args) {
        List<CallbackThread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new CallbackThread());
        }

        ExecutorService executorService = Executors.newFixedThreadPool(30);

        List<Future<ThreadResult>> result = new ArrayList<>();
        for (int i = 0; i < threads.size(); i++) {
            result.add(executorService.submit(threads.get(i)));
        }

        System.out.println("队列排队中............");

        for (int i = 0; i < result.size(); i++) {
            try {
                ThreadResult threadResult = result.get(i).get();
                System.out.println("threadResult : " + threadResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
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
