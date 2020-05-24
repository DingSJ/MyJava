package mqtest.thread.threadInterrupt2;

/**
 * 多线程执行任务
 *
 *  任务超时或者异常中断所有线程
 *
 * */
public class TestMain2 {
    public static void main(String[] args) {

        Thread t1 = new Thread(new MyRunnable1(), "线程一");
        Thread t2 = new Thread(new MyRunnable2(), "线程二");
        Thread t3 = new Thread(new MyRunnable3(), "线程三");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("主线程执行完毕");

    }
}
