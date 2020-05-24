package mqtest.thread.statistics;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadStep3 extends Thread {
    private List<ThreadResult> threadResults;
    private CountDownLatch latch;
    public ThreadStep3(List<ThreadResult> threadResults, CountDownLatch latch) {
        this.threadResults = threadResults;
        this.latch = latch;
    }

    @Override
    public void run() {
        ThreadResult threadResult = null;
        try {
            System.out.println("步骤三 Start .... ");

            for (int i = 0; i < 10; i++) {
                System.out.println("步骤三： " + i);
            }

            int i = 1 / 0;
            threadResult = new ThreadResult(true,currentThread().getName(),"SUCCESS","处理成功");
            System.out.println("步骤三 End .... ");
        } catch (Exception e) {
            System.out.println("线程三 XXXXXX" + e.getMessage());
            e.printStackTrace();
            threadResult = new ThreadResult(false,currentThread().getName(),"ERROR","处理失败");
        } finally {
            threadResults.add(threadResult);
            latch.countDown();
        }
    }
}
