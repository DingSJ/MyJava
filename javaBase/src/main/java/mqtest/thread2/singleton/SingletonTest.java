package mqtest.thread2.singleton;

import java.util.ArrayList;
import java.util.List;

public class SingletonTest {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(){
                @Override
                public void run() {
                    System.out.println( SingletonDemo6.getInstance() + " : " + SingletonDemo6.getInstance().getFlag());
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

//        for (int i = 0; i < threads.size(); i++) {
//            try {
//                threads.get(i).join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        threads.clear();
        System.out.println("=========================================");

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(){
                @Override
                public void run() {
                    System.out.println( SingletonDemo6.getInstance() + " : " + SingletonDemo6.getInstance().getFlag());
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
