package mqtest.thread2.countdownlatch;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerTest {
    public static void main(String[] args) {

        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String aa = exchanger.exchange("Thread A Data");
                    System.out.println(Thread.currentThread().getName() + " - 获取线程数据交换 :" + aa);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String bb = exchanger.exchange("Thread B Data");
                    System.out.println(Thread.currentThread().getName() + " - 获取线程数据交换 :" + bb);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String cc = exchanger.exchange("Thread C Data",100, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() + " - 获取线程数据交换 :" + cc);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(30);
                    String dd = exchanger.exchange("Thread D Data");
                    System.out.println(Thread.currentThread().getName() + " - 获取线程数据交换 :" + dd);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "dd").start();
    }
}
