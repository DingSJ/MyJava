package com.china.test.thread2.procducerConsumer;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MessageLinkedList {
    private final static int DEFAULT_MAX_CAPACITY = 100;
    private final int limit;
    private final LinkedList<Message> dataList;
    private final Random random = new Random(System.currentTimeMillis());

    public MessageLinkedList(){
        this(DEFAULT_MAX_CAPACITY);
    }

    public MessageLinkedList(final int size) {
        this.limit = size;
        this.dataList = new LinkedList<>();
    }

    public void put(Message message) throws InterruptedException {
        synchronized (dataList) {
            while (getSize() >= limit) {
                dataList.wait();
            }

            dataList.addLast(message);
            dataList.notifyAll();
        }
    }

    public Message get() throws InterruptedException {
        synchronized (dataList) {
            Message message;
            while (dataList.isEmpty()) {
                dataList.wait();
            }
            message = dataList.removeFirst();
            dataList.notifyAll();
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            return message;
        }
    }

    public int getSize() {
        synchronized (dataList) {
            return dataList.size();
        }
    }
}
