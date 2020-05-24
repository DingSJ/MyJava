package mqtest.thread.threadInterrupt2;

import java.util.concurrent.TimeUnit;

public class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("线程222222222222执行任务 ：" + i);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("中断异常222222222222");
            }
        }
        System.out.println("线程222222222222执行完毕+++++++++++++++++");
    }
}
