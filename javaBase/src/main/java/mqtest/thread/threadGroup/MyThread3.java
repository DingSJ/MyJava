package mqtest.thread.threadGroup;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThread3 extends Thread {
    private AtomicBoolean flag;
    public MyThread3(AtomicBoolean flag) {
        this.flag = flag;
    }

    public void setFlag(AtomicBoolean flag) {
        this.flag = flag;
    }

    public AtomicBoolean getFlag() {
        return flag;
    }

    @Override
    public void run() {
        while (flag.get()) {
            System.out.println(".... " + currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("系统中断 : " + Thread.currentThread().getName());
            }
            int i = 1 / 0;
        }
    }
}
