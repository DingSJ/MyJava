package mqtest.thread.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 获取多个线程的执行结果，并设置起止位置
 * */
public class ThreadStepMain {
    private static volatile List<ThreadResult> threadResults = new ArrayList<>();
    private static final CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {

        System.out.println("主程序运行开始...............");
        try {
            Thread t1 = new ThreadStep1(threadResults, latch);
            Thread t2 = new ThreadStep2(threadResults,latch);
            Thread t3 = new ThreadStep3(threadResults,latch);

            t1.setUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
            t2.setUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
            t3.setUncaughtExceptionHandler(new StateUncaughtExceptionHandler());

            t1.setName("线程一");
            t1.start();
            t2.setName("线程二");
            t2.start();
            t3.setName("线程三");
            t3.start();

            latch.await();
        }catch (Exception ee) {
            System.out.println("主线程捕获异常YYYY");
            ee.printStackTrace();
        }

        System.out.println(threadResults);

        for (int i = 0; i < 10; i++) {
            System.out.println("主程序：" + i);
        }

        System.out.println("主程序处理结束 .......");
    }
}
