package com.china.test.thread.statistics;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadStep1 extends Thread {
    private List<ThreadResult> threadResults;
    private CountDownLatch latch;
    public ThreadStep1(List<ThreadResult> threadResults, CountDownLatch latch) {
        this.threadResults = threadResults;
        this.latch = latch;
    }

    @Override
    public void run() {
        ThreadResult threadResult = null;
        try {
            System.out.println("步骤一 Start .... ");

            for (int i = 0; i < 10; i++) {
                System.out.println("步骤一： " + i);
            }

            threadResult = new ThreadResult(true,currentThread().getName(),"SUCCESS","处理成功");
            System.out.println("步骤一 End .... ");
        } catch (Exception e) {
            System.out.println("线程一 XXXXXX" + e.getMessage());
            e.printStackTrace();
            threadResult = new ThreadResult(false,currentThread().getName(),"ERROR","处理失败");
        } finally {
            threadResults.add(threadResult);
            latch.countDown();
        }
    }
}
