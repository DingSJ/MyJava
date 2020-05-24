package mqtest.thread2.producerConsumerThread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 多线程版本生产者消费者
 * */
public class ThreadProConTest {

    private static final Lock LOCK = new ReentrantLock();
    private static final Condition PRO_CON = LOCK.newCondition();
    private static final Condition CON_CON = LOCK.newCondition();
    private static final LinkedList<Long> DATA_LIST = new LinkedList<>();
    private static final Integer LIMIT = 20;
    private static volatile Long RES = 1L;

    public static void main(String[] args) {
        IntStream.range(0,6).boxed().forEach(ThreadProConTest::beginProducer);
        IntStream.range(0,3).boxed().forEach(ThreadProConTest::beginConsumer);
    }

    public static void beginProducer(int i){
        new Thread(()->{
            while (true) {
                producer();
                sleep(3);
            }
        }," -P-" + i + " ").start();
    }

    public static void beginConsumer(int i){
        new Thread(()->{
            while (true) {
                consumer();
                sleep(2);
            }
        }," -C-" + i + " ").start();
    }


    public static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** 生产 */
    public static void producer(){
        try {
            LOCK.lock();
            while (DATA_LIST.size() >= LIMIT) {
                PRO_CON.await();
            }
            DATA_LIST.add(RES);
            System.out.println(Thread.currentThread().getName() + " -PV : " + RES);
            RES++;
            CON_CON.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
    /** 消费 */
    public static void consumer(){
        try {
            LOCK.lock();
            while (DATA_LIST.size() == 0) {
                CON_CON.await();
            }
            Long value = DATA_LIST.removeFirst();
            System.out.println(Thread.currentThread().getName() + " -CV : " + value);
            PRO_CON.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
