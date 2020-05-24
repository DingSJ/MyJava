package mqtest.thread2.procducerConsumer;

public class Consumer extends Thread{
    private int sequence;
    private String name;
    private MessageLinkedList linkedList;
    public Consumer(MessageLinkedList dataList,int sequence, String name) {
        super(name);
        this.linkedList = dataList;
        this.sequence = sequence;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = linkedList.get();
                System.out.println(currentThread().getName()+ "-"+ sequence + " : " + message.getData());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
