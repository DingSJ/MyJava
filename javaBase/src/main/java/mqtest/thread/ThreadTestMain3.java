package mqtest.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * 线程启动，控制顺序 - {@link java.util.concurrent.CyclicBarrier}
 * */
public class ThreadTestMain3 {
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
    public static void main(String[] args) {
        Thread thread = new MyThread3(cyclicBarrier);
        thread.start();

        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("当前执行：" + i);
        }

        System.out.println("程序结束");
    }
}
