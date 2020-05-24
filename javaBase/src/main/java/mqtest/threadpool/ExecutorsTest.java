package mqtest.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 线程异常捕获
 *  1. Thread # setUncaughtExceptionHandler(new MyException());
 *                                      Thread.UncaughtExceptionHandler
 *
 *  2. 模板类
 *
 * */
public class ExecutorsTest {
    public static void main(String[] args) {

         testExecutorError();
        testThreadError();

    }

    private static void testThreadError() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            Thread t = new MyThread2(i);
            t.setUncaughtExceptionHandler(new MyException());
            executorService.submit(t);
        }

        executorService.shutdown();
    }

    static class MyThread2 extends Thread{
        private int no;

        public MyThread2(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                if (no % 2 == 0) {
                    int tmp = no / 0;
                    System.out.println("tmp: " + tmp);
                }
                System.out.println(no + " - Success");
            } catch (Exception e) {
                System.out.println(no + " - calculate error..");
            }
        }
    }


    private static void testExecutorError() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        IntStream.range(0, 20).boxed().forEach(i -> executorService.submit(
                new MyThread(i) {
                    @Override
                    protected void done() {
                        if (i % 4 == 0) {
                            int tmp = 1 / 0;
                        }
                        System.out.println(i + ": 处理完成 - SUCCSESS");
                    }

                    @Override
                    protected void error(Exception e) {
                        System.out.println(i + ": 处理失败 - ERROR");
                    }

                    @Override
                    protected void init() {
                        // do nothing...
                    }
                }
        ));

        executorService.shutdown();
    }

    public abstract static class MyThread implements Runnable{
        private int no;
        public MyThread(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.init();
                this.done();
            } catch (Exception e) {
                this.error(e);
            }
        }

        protected abstract void done();

        protected abstract void error(Exception e);

        protected abstract void init();
    }

    private static class MyException implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(" Error ");
        }
    }
}
