package mqtest.thread.futureException;

import mqtest.thread.statistics.ThreadResult;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallbackThread implements Callable<ThreadResult> {
    @Override
    public ThreadResult call() throws Exception {
        ThreadResult result;
        try {
            System.out.println(Thread.currentThread().getName() + ": 执行开始");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
            }
            result = new ThreadResult(true,Thread.currentThread().getName(),"SUCCESS","执行成功");
            System.out.println(Thread.currentThread().getName() + ": 执行结束");
        } catch (Exception e) {
            result = new ThreadResult(true,Thread.currentThread().getName(),"SUCCESS","执行成功");
            System.out.println("执行失败。。。。" + e.getMessage());
        }
        return result;
    }
}
