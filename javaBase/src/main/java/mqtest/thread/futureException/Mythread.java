package mqtest.thread.futureException;

public class Mythread extends Thread {

    @Override
    public void run() {
        System.out.println(currentThread().getName() + ": 执行开始");

        for (int i = 0; i < 10; i++) {
            System.out.println(currentThread().getName() + " - " + i);
        }

        System.out.println(currentThread().getName() + ": 执行结束");
    }
}
