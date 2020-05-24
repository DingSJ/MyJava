package mqtest.thread2.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 单例： 懒汉式，线程安全
 * */
public class SingletonDemo4 {
    private static volatile SingletonDemo4 INSTANCE;
    private boolean flag = Boolean.FALSE;
    private SingletonDemo4(){
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("构造函数睡眠1秒");
                TimeUnit.SECONDS.sleep(1);
            }
            this.flag = Boolean.TRUE;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("构造函数初始化完成");
    }

    public boolean getFlag(){
        return this.flag;
    }
    public static SingletonDemo4 getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonDemo4.class) {
//                System.out.println("判断null");
                if (INSTANCE == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new SingletonDemo4();
                }
            }
        }
        return INSTANCE;
    }

}
