package com.china.test.thread.threadInterrupt2;

import java.util.ArrayList;
import java.util.List;

public class ThreadService {
    private List<Thread> threadList = new ArrayList<>();
    public void start(List<Runnable> runnables) {
        Thread thread;
        for (Runnable runnable : runnables) {
            thread = new Thread(runnable);
            thread.setDaemon(true);
            threadList.add(thread);

            thread.start();
        }
    }

    public void shutdown(long mills){
        long start = System.currentTimeMillis();
        long end = start + mills;
        while (System.currentTimeMillis() > end) {
            for (Thread thread : threadList) {
                while (!thread.isInterrupted()) {
                    thread.interrupt();
                }
            }
        }

    }
}
