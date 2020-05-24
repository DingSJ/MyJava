package mqtest.thread2.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 单例： 懒汉  volatile 修饰变量，防止类没完全初始化
 * */
public class SingletonDemo3 {
    private static volatile SingletonDemo3 INSTANCE;
    private SingletonDemo3(){

    }

    public static SingletonDemo3 getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonDemo3.class) {
                System.out.println("判断null");
                if (INSTANCE == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new SingletonDemo3();
                }
            }
        }
        return INSTANCE;
    }

}
