package mqtest.threadTest;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class OrderTest {
    public static void main(String[] args) throws InterruptedException {

//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,
//                10,
//                10,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(100),
//                r -> {
//                    Thread thread = new Thread(r);
//                    thread.start();
//                    return thread;
//                },
//                new ThreadPoolExecutor.CallerRunsPolicy());


        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                return thread;
            }
        });

        MQUtil mqUtil = new MQUtil();
//        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            poolExecutor.submit(() -> {
            new Order(mqUtil);
            });
        }

        while (poolExecutor.getCompletedTaskCount() != 100) {
            Thread.sleep(0);
        }

        mqUtil.close();
        poolExecutor.shutdown();
        System.out.println("下单结束");
    }
}
