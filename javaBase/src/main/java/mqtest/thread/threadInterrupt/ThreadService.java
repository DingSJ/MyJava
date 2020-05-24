package mqtest.thread.threadInterrupt;

/**
 * 线程服务，将子线程设置为守护线程
 * */
public class ThreadService {

    private Thread thread;

    public void start(Runnable runnable){
        thread = new Thread(runnable);
        thread.setDaemon(true);

        thread.start();
    }

    public void shutdown(long mills){
        long now = System.currentTimeMillis();
        long end = now + mills;
        while (!thread.isInterrupted()) {
            if (System.currentTimeMillis() > end) {
                thread.interrupt();
            }
        }
    }
}
