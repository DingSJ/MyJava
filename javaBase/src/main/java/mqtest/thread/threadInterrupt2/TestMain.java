package mqtest.thread.threadInterrupt2;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程执行任务
 *
 *  任务超时或者异常中断所有线程
 *
 * */
public class TestMain {
    public static void main(String[] args) {

        ThreadService service = new ThreadService();
        List<Runnable> runnables = new ArrayList<>();
        runnables.add(new MyRunnable1());
        runnables.add(new MyRunnable2());
        runnables.add(new MyRunnable3());
        service.start(runnables);
//        service.shutdown(8000);

        System.out.println("主线程执行完毕");

    }
}
