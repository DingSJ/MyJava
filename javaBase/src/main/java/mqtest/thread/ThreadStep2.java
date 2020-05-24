package mqtest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadStep2 extends Thread {
    private CountDownLatch countDownLatch;
    private AtomicInteger count;
    public ThreadStep2(CountDownLatch countDownLatch, AtomicInteger count) {
        this.countDownLatch = countDownLatch;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            System.out.println("步骤二 Start .... ");

            for (int i = 0; i < 10; i++) {
                System.out.println("步骤二： " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.getAndIncrement();
            System.out.println("步骤二 End .... ");
        }finally {
            countDownLatch.countDown();
        }

    }
}
