package mqtest.threadpool;

import mqtest.thread.MyThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程决绝策略
 * {@link ThreadPoolExecutor#CallerRunsPolicy}
 * AbortPolicy
 * DiscardPolicy
 * DiscardOldestPolicy
 * */
public class AbortPolicyTest {
    public static void main(String[] args) {

        // 扔掉任务，抛出异常
        RejectedExecutionHandler handler1 = new ThreadPoolExecutor.AbortPolicy();
        // 回调线程执行
        RejectedExecutionHandler handler2 = new ThreadPoolExecutor.CallerRunsPolicy();
        // 扔掉旧任务执行当前任务
        RejectedExecutionHandler handler3 = new ThreadPoolExecutor.DiscardOldestPolicy();
        // 扔掉任务不做处理
        RejectedExecutionHandler handler4 = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor executor = buildThreadPoolExecutor(handler3);

        for (int i = 0; i < 5; i++) {
            Thread t = new MyThread(i);
            executor.submit(t);
        }
    }

    private static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
            System.out.println(Thread.currentThread().getName() + "-====-");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ThreadPoolExecutor buildThreadPoolExecutor(RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(1,2,60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),(i) -> new Thread(i), handler);
    }
}
