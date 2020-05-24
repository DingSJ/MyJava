package mqtest.thread;

public class TestRun {

    public static void main(String[] args) {
        try {
            MyThread0 myThread = new MyThread0();
            MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
            myThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread0 extends Thread{
    private String num = "a";

    public MyThread0() {
        super();
    }

    public MyThread0(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        int numInt = Integer.parseInt(num);
        System.out.println("在线程中打印："+ (numInt+1));
    }
}