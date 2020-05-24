package mqtest.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池核心参数
 *
 *  将任务放在队列里，核心线程池线程去执行，若是核心线程空闲，核心线程就去执行新任务，
 *  若是核心线程池都在执行任务，就将任务放在队列里面，若是队列已满，就新建线程去执行
 *
 *  若是新建线程达到线程最大值，就将任务放在队列里面，若是线程池已满，执行拒绝策略（CallerRunsPolicy，
 *                                                DiscardOldestPolicy，DiscardPolicy，AbortPolicy）
 *
 *  {@link ThreadPoolExecutor}(int corePoolSize, int maximumPoolSize,// 核心线程数，最大线程数
*                      long keepAliveTime, TimeUnit unit,            // 空闲线程存活时间值，时间单位
*                      BlockingQueue<Runnable> workQueue,            // 工作队列 SynchronousQueue LinkedBlockingQueue ArrayBlockingQueue
*                      ThreadFactory threadFactory,                  // 线程工厂
*                      RejectedExecutionHandler handler)             // 拒绝策略
 *
 */
public class ExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = buildExecutorService();

        executorService.submit(() -> sleep(2));
        executorService.submit(() -> sleep(5));
        executorService.submit(() -> sleep(6));
        executorService.submit(() -> sleep(7));
        executorService.submit(() -> sleep(7));
        executorService.submit(() -> sleep(10));
        executorService.submit(() -> sleep(12));
        executorService.submit(() -> sleep(15));

        int activeSize = -1;
        int queueSize = -1;
        while (true) {
            if (activeSize != executorService.getActiveCount() || queueSize != executorService.getQueue().size()) {
                printMsg(executorService);
                activeSize = executorService.getActiveCount();
                queueSize = executorService.getQueue().size();

                if (queueSize == 0 && executorService.getActiveCount() == 0) {
                    executorService.shutdown();
                    break;
                }
            }
        }
    }

    public static void printMsg(ThreadPoolExecutor executorService) {
        System.out.println("====================================================");
        System.out.println("ActiveCount: " + executorService.getActiveCount());
        System.out.println("CorePoolSize: " + executorService.getCorePoolSize());
        System.out.println("PoolSize: " + executorService.getPoolSize());
        System.out.println("QueueSize: " + executorService.getQueue().size());
        System.out.println("TaskCount: " + executorService.getTaskCount());
        System.out.println(">>");
    }


    public static ThreadPoolExecutor buildExecutorService(){
        /* ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                              long keepAliveTime, TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
        */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), r -> {
                Thread t = new Thread(r);
                return t;
        }, new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println("* " + Thread.currentThread().getName() + " *");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
