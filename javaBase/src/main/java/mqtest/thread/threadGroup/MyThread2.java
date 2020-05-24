package mqtest.thread.threadGroup;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThread2 extends Thread {

    private AtomicBoolean flag;
    public MyThread2(AtomicBoolean flag) {
        this.flag = flag;
    }
    @Override
    public void run() {
        while (flag.get()) {
            try {
                System.out.println(".... " + Thread.currentThread().getName() + ": interrupted： " + Thread.currentThread().isInterrupted());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("系统中断 : " + Thread.currentThread().getName());
            }
        }
    }
}
