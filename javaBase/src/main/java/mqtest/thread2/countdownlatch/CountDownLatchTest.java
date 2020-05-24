package mqtest.thread2.countdownlatch;

import java.util.concurrent.*;

/**
 *  {@link CountDownLatch} 全部任务执行完了才结束
 *  {@link CyclicBarrier} 所有线程到执行点才开始一块执行
 *
 * */
public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "等待10秒");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Thread.currentThread().getName() + " Over");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程一").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "等待3秒");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + " Over");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程二").start();

        try {
//            cyclicBarrier.await(5, TimeUnit.SECONDS);
            countDownLatch.await();
            System.out.println("StaticMain over !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread("ccccc"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " CCCC");
            }
        }.start();
    }

    /*public void mqtest() {

        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), null, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();

    }*/
}
