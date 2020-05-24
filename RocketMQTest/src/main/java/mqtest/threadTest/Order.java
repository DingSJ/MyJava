package mqtest.threadTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 模拟下单过程
 * */
public class Order {

    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private MQUtil mqUtil = null;
    public Order(MQUtil mqUtil) {
        this.mqUtil = mqUtil;
        this.orderStart();
    }


    /**
     *  下单入口
     * */
    private void orderStart() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - 下单开始");
        try {

            long start = System.currentTimeMillis();
            long orderId = start - 1_000_000 + new Random().nextInt();

            // 校验参数
            this.chkParam(mqUtil,threadName,orderId);

            // 扣减库存
            this.deduct(mqUtil,threadName,orderId);

            // 下单成功
            this.order(mqUtil,threadName,orderId);

            // 发送成功消息
            this.sendMsg(mqUtil, threadName, orderId);

            // 下单完成
            this.orderEnd(mqUtil, threadName,orderId);

            long end = System.currentTimeMillis();
            System.out.println(threadName + " - 下单结束, " + "耗时 ：" + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void orderEnd(MQUtil mqUtil, String threadName, long orderId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(500));
        mqUtil.sendMessgge(threadName + " - " + "发送完成 ： " + orderId, threadName, orderId);
    }

    private void sendMsg(MQUtil mqUtil, String threadName, long orderId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(500));
        mqUtil.sendMessgge(threadName + " - " + "发送下单消息 ： " + orderId, threadName, orderId);
    }

    private void order(MQUtil mqUtil, String threadName, long orderId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(500));
        mqUtil.sendMessgge(threadName + " - " + "下单开始 ： " + orderId, threadName, orderId);
    }

    private void deduct(MQUtil mqUtil, String threadName, long orderId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(500));
        mqUtil.sendMessgge(threadName + " - " + "库存扣减 ： " + orderId,threadName, orderId);
    }

    private void chkParam(MQUtil mqUtil, String threadName, long orderId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(500));
        mqUtil.sendMessgge(threadName + " - " + "校验参数 ： " + orderId,threadName, orderId);
    }
}
