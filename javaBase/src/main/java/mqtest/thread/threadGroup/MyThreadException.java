package mqtest.thread.threadGroup;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyThreadException implements Thread.UncaughtExceptionHandler {

    private AtomicBoolean flag;
    public MyThreadException(AtomicBoolean flag) {
        this.flag = flag;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        System.out.println("全局处理异常。。。。" + e.getMessage() );
        System.out.println("关闭所有线程。。。。");
        t.getThreadGroup().interrupt();
        this.flag.set(false);
    }
}
