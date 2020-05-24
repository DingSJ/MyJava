package mqtest.thread.statistics;

public class StateUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("静态的异常处理" + t.getName());
    }
}