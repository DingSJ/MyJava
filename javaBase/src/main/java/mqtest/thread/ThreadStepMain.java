package mqtest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadStepMain {
    private static final CountDownLatch countDownLatch = new CountDownLatch(3);
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        System.out.println("主程序运行开始...............");
        Thread t1 = null;
        Thread t2 = null;
        Thread t3 = null;
        try {
            t1 = new ThreadStep1(countDownLatch, count);
            t2 = new ThreadStep2(countDownLatch, count);
            t3 = new ThreadStep3(countDownLatch, count);

            t1.setUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
            t2.setUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
            t3.setUncaughtExceptionHandler(new StateUncaughtExceptionHandler());

            t1.start();
            t1.setName("线程一");
            t2.start();
            t1.setName("线程二");
            t3.start();
            t1.setName("线程三");

            countDownLatch.await();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception ee) {
            ee.printStackTrace();
        }


        System.out.println("任务执行成功： " + count);

        if (count.get() != 3) {
            throw new RuntimeException("多线程异常");
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("主程序：" + i);
        }

        System.out.println("主程序处理结束 .......");
    }
}
