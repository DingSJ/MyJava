package com.china.test.thread2.wait2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MyResource {
    private volatile List<String> resourceList = new ArrayList<>();

    private Object LOCK = new Object();
    private volatile boolean isProduced = false;

    public void produceResource() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                String resource = UUID.randomUUID().toString();
                resourceList.add(resource);
                System.out.println("+++++  : " + resource);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isProduced = true;
                LOCK.notify();
            }
        }
    }

    public void consumeResource() {
        synchronized (LOCK) {
            if (isProduced) {
                String resource = resourceList.get(new Random().nextInt(resourceList.size()));
                resourceList.remove(resource);
                System.out.println("-----  : " + resource);
                isProduced = false;
                LOCK.notify();
            }else{
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
