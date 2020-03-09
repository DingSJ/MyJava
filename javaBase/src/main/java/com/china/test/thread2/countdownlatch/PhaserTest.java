package com.china.test.thread2.countdownlatch;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 可动态添加新任务
 * */
public class PhaserTest {
    private static final int COUNT = 5;

    public static void main(String[] args){

        final Phaser phaser = new Phaser(COUNT);
        for (int i = 0; i < COUNT; i++) {
            new MyPhaser(i,phaser).start();
        }
    }

    static class MyPhaser extends Thread{
        private int num;
        private Phaser phaser;

        public MyPhaser(int num, Phaser phaser) {
            this.num = num;
            this.phaser = phaser;
        }

        public static void sleep(int seconds){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            // 1. 步骤1
            System.out.println("Num: " + num + " - " + " Step 1");
            phaser.arriveAndAwaitAdvance();
            sleep(1);
            // 1. 步骤2
            System.out.println("Num: " + num + " - " + " Step 2");
            phaser.arriveAndAwaitAdvance();
            sleep(1);
            // 1. 步骤3
            System.out.println("Num: " + num + " - " + " Step 3");
            phaser.arriveAndAwaitAdvance();
            sleep(1);
            // 1. 步骤4
            System.out.println("Num: " + num + " - " + " Step 4");
            phaser.arriveAndAwaitAdvance();
            sleep(1);
            // 1. 步骤5
            System.out.println("Num: " + num + " - " + " Step 5");
            phaser.arriveAndAwaitAdvance();
            sleep(1);
            System.out.println("Num: " + "== OVER ==");
        }
    }
}
