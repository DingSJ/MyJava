package mqtest.thread2.wait2;

public class TestMain {
    public static void main(String[] args) {
        MyResource resource = new MyResource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    resource.produceResource();
                }
            }
        }, "Pro").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    resource.consumeResource();
                }
            }
        }, "Con").start();
    }
}
