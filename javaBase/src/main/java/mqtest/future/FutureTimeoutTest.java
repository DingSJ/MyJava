package mqtest.future;

import java.util.concurrent.*;

public class FutureTimeoutTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> task = executorService.submit(()->{
            System.out.println("Start...");
            Thread.sleep(5_000);
            System.out.println("End...");
            return 1;
        });

        try {
            Integer result = task.get(3, TimeUnit.SECONDS);
//            Integer result = task.get();
            System.out.println( "Result: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

            /**
             * 任务取消
             * */
            task.cancel(true);
        }
        System.out.println(task.isCancelled());
        System.out.println(task.isDone());
        executorService.shutdown();
    }
}
