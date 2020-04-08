package com.china.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 测试队列特性
 * 空值，顺序，存值，取值
 */
public class BlockingQueueTest {


    /**
     * 初始需要指定容器长度，后期不可改变
     */
    @Test
    public void testArrayBlockingQueue() {
        ArrayBlockingQueue<String> arrayQueue = new ArrayBlockingQueue<>(3);

//        arrayQueue.add(null);
//        java.lang.NullPointerException

        boolean offer = arrayQueue.offer("1");
        boolean offer1 = arrayQueue.offer("2");
        boolean offer2 = arrayQueue.offer("3");
        boolean offer3 = arrayQueue.offer("4");
//        boolean offer4 = arrayQueue.add("5");  // java.lang.IllegalStateException: Queue full
        System.out.println(offer);
        System.out.println(offer1);
        System.out.println(offer2);
        System.out.println(offer3);
        // true true true false

        arrayQueue.stream().forEach(System.out::println);
//        1     2     3

        arrayQueue.clear();
        System.out.println("-----------");
        try {
            arrayQueue.put("a1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arrayQueue.offer("a2");
        arrayQueue.add("a3");
        arrayQueue.stream().forEach(System.out::println);
//        a1 a2 a3
    }


    /**
     *
     */
    @Test
    public void testLinkedListBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<String> linkedQueue = new LinkedBlockingQueue<>(3);
        linkedQueue.put("aaa");
        linkedQueue.add("bbb");
        linkedQueue.offer("ccc");

//        linkedQueue.add("dddd"); // java.lang.IllegalStateException: Queue full
        linkedQueue.stream().forEach(System.out::print);
        // aaa  bbb ccc

        System.out.println("\n");
        System.out.println(linkedQueue.poll());
        linkedQueue.stream().forEach(System.out::print);
        //aaa  bbb ccc
        System.out.println("\n");
        String take = linkedQueue.take();
        System.out.println("take: " + take);
        // take: bbb
    }

    /**
     *
     */
    @Test
    public void testPriorityBlockingQueue() throws InterruptedException {
        PriorityBlockingQueue<String> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.put("aaa");
        priorityQueue.add("bbb");
        priorityQueue.offer("ccc");

        priorityQueue.stream().forEach(System.out::println);
        System.out.println("111111111111111111111111");
        System.out.println(priorityQueue.peek());
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.remove());
    }

    /**
     * take 只能取出超时的元素
     * pull 可以直接返回队列第一个元素
     *
     */
    @Test
    public void testDelayQueue() throws InterruptedException {
        DelayQueue<DelayDemo<String>> delayQueue = new DelayQueue<>();
        delayQueue.add(new DelayDemo("aaa", 3000));
        delayQueue.add(new DelayDemo("aaa", 5000));

        System.out.println("----");
        System.out.println(delayQueue.take());

    }

    /**
     * DelayQueue 的元素要求实现  Delayed
     */
    class DelayDemo<T> implements Delayed {
        private T ele;
        private long timeout;

        public DelayDemo(T ele, long timeout) {
            this.ele = ele;
            this.timeout = timeout + System.currentTimeMillis();
        }

        public DelayDemo() {
        }

        public T getEle() {
            return ele;
        }

        public void setEle(T ele) {
            this.ele = ele;
        }

        public long getTimeout() {
            return timeout;
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = timeout - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MICROSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayDemo temp = (DelayDemo) o;
            return this.timeout - temp.timeout > 0 ? -1 : 1;
        }

        public T of(T t, long timeout) {
            return (T) new DelayDemo(t, timeout);
        }
    }
}

