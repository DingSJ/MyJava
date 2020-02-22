package com.china.test.thread2.procducerConsumer;

import java.util.concurrent.atomic.AtomicInteger;

public class ClientMain {
    public static void main(String[] args) {
        MessageLinkedList linkedList = new MessageLinkedList(30);
        AtomicInteger atomicCount = new AtomicInteger(1);

        new Producer(linkedList, atomicCount, 1,"Pro").start();
        new Producer(linkedList, atomicCount, 2,"Pro").start();
        new Producer(linkedList, atomicCount, 3,"Pro").start();
        new Producer(linkedList, atomicCount, 4,"Pro").start();
        new Producer(linkedList, atomicCount, 5,"Pro").start();
        new Producer(linkedList, atomicCount, 6,"Pro").start();
        new Producer(linkedList, atomicCount, 7,"Pro").start();
        new Producer(linkedList, atomicCount, 8,"Pro").start();
        new Producer(linkedList, atomicCount, 9,"Pro").start();
        new Producer(linkedList, atomicCount, 10,"Pro").start();
        new Consumer(linkedList, 1, "Con").start();
    }
}
