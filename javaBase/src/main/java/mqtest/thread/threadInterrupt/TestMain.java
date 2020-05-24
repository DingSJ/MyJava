package mqtest.thread.threadInterrupt;

import java.util.concurrent.TimeUnit;

public class TestMain {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        service.start(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("线程一执行任务");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("中断异常XX");
                    }
                }
            }
        });
        service.shutdown(5000);
        System.out.println("主线程完毕。。。");
    }
}
