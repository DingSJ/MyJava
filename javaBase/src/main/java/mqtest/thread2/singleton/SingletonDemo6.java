package mqtest.thread2.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 枚举类： 推荐
 * */
public class SingletonDemo6 {
    private boolean flag = Boolean.FALSE;
    public SingletonDemo6(){
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

    public static SingletonDemo6 getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

}
