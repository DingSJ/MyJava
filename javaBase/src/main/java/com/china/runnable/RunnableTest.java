package com.china.runnable;

import java.util.List;
import java.util.concurrent.*;

public class RunnableTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = ()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("Interrupted>>>  ");
                return "Fail";
            }
            return "Success";
        };

        Callable<String> call2 = ()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("Interrupted>>>  ");
                return "Fail";
            }
            return "Success";
        };
        System.out.println("任务开始...");
        Future<String> submit = executorService.submit(callable);
        Future<String> s2 = executorService.submit(call2);
        String result = null;
        try {
            result = submit.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("result get timeout...");
            System.out.println("submit done: " + submit.isDone());
            result = "";
        }
        System.out.println("result : " + result);

        // 等待所有任务执行完毕再关闭线程池
//        executorService.shutdown();

        // 立刻结束已经在运行的任务，并返回在等待被执行的任务
        List<Runnable> runnables = executorService.shutdownNow();
        System.out.println(runnables.size());
    }
}
