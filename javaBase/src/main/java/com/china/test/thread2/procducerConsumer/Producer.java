package com.china.test.thread2.procducerConsumer;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Thread{
    private String name;
    private int sequence;
    private MessageLinkedList linkedList;
    private AtomicInteger atomicCount;
    public Producer(MessageLinkedList dataList,AtomicInteger atomicCount, int sequence,String name) {
        super(name);
        this.linkedList = dataList;
        this.atomicCount = atomicCount;
        this.sequence = sequence;
    }

    @Override
    public void run() {
        while (true) {
            Message message = new Message("My Cake - " + atomicCount.getAndIncrement());
            try {
                linkedList.put(message);
                System.out.println(currentThread().getName() + "-"+ sequence + " : " + message.getData() + " - size:" + linkedList.getSize());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
