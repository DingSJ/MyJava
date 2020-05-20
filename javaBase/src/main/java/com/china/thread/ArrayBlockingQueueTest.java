package com.china.thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 测试并发容器 {@link java.util.concurrent.ArrayBlockingQueue}
 * 有界队列
 *
 * BlockingQueue接口提供了3个添加元素方法。
     add：添加元素到队列里，添加成功返回true，由于容量满了添加失败会抛出IllegalStateException异常
     offer：添加元素到队列里，添加成功返回true，添加失败返回false
     put：添加元素到队列里，如果容量满了会阻塞直到容量不满

 * 3个删除方法。
     poll：删除队列头部元素，如果队列为空，返回null。否则返回元素。
     remove：基于对象找到对应的元素，并删除。删除成功返回true，否则返回false
     take：删除队列头部元素，如果队列为空，一直阻塞到队列有元素并删除

 * size
 * remainingCapacity
 * contains
 * toArray
 * toArray
 * toString
 * clear
 * drainTo
 * drainTo

 常用的阻塞队列具体类有
    ArrayBlockingQueue、LinkedBlockingQueue、
    PriorityBlockingQueue、LinkedBlockingDeque等。

 ArrayBlockingQueue的原理就是使用一个可重入锁和这个锁生成的两个条件对象进行并发控制(classic two-condition algorithm)。

 ArrayBlockingQueue是一个带有长度的阻塞队列，初始化的时候必须要指定队列长度，且指定长度之后不允许进行修改。
 *
 * */
public class ArrayBlockingQueueTest {

    /** 删除队列头部元素，如果队列为空，返回null。否则返回元素。 */
    @Test
    public void testPoll(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        try {
            String poll = queue.poll();
            System.out.println("poll : " + poll);
        } catch (Exception e) {
            System.out.println("异常了: " + e);
            e.printStackTrace();
        }
    }
    /** 基于对象找到对应的元素，并删除。删除成功返回true，否则返回false */
    @Test
    public void testRemove(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        try {
            boolean value = queue.remove("Hello-1");
            System.out.println("poll : " + value);
        } catch (Exception e) {
            System.out.println("异常了: " + e);
            e.printStackTrace();
        }
    }

    /** 删除队列头部元素，如果队列为空，一直阻塞到队列有元素并删除 */
    @Test
    public void testTake() throws InterruptedException {
//        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(1);

        // 3秒后放一个元素
        Runnable runnable = () -> {
            try {
                queue.put("hello-put");
                boolean offer = queue.offer("hello-put");
                System.out.println("offer: " + offer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable runnable2 = ()->{
            queue.add("hello-add");
        };
        Executors.newScheduledThreadPool(1).schedule(runnable, 3, TimeUnit.SECONDS);
        Executors.newScheduledThreadPool(1).schedule(runnable2, 1, TimeUnit.SECONDS);

        String pollValue = queue.poll(2,TimeUnit.SECONDS);

        // 会阻塞
        String value = queue.take();

        System.out.println("pollValue : " + pollValue);
        System.out.println("value : " + value);

    }

    @Test
    public void testLindedPut() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
        queue.put("1");
        queue.put("2");
        queue.put("3");

        // 阻塞

        System.out.println(queue.size());
        queue.stream().forEach(System.out::println);
    }
    @Test
    public void testLindedPut2() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
        Executors.newScheduledThreadPool(1).schedule(()->{
            while (true) {
                String poll = queue.poll();
                if (poll != null && !"".equals(poll)) {
                    System.out.println("poll: " + poll);
                    try {
                        TimeUnit.SECONDS.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 3, TimeUnit.SECONDS);
        queue.put("1");
        queue.put("2");
        queue.put("3");



        System.out.println(queue.toArray());
        queue.stream().forEach(System.out::println);
    }
}
