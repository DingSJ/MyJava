package mqtest.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 线程启动，控制顺序 - CountDownLatch
 * */
public class ThreadTestMain2 {
    private static final CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        Thread thread = new MyThread2(latch);
        thread.start();

        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("程序异常");
            return;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("当前执行：" + i);
        }



        System.out.println("程序结束");
    }
}
