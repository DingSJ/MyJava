package mqtest.threadTest;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OrderConsume {
    private static final String MQ_GROUP_SEND_MSG = "Send_Msg";
    private static final String MQ_TOPIC = "My-DefaultCluster-Order";
    private static final String MQ_TAG = "Tag_B";
    private static final String NAME_SERVER_ADD = "192.168.47.131:9876;192.168.47.132:9876";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQ_GROUP_SEND_MSG);
        consumer.setNamesrvAddr(NAME_SERVER_ADD);
        consumer.setMessageModel(MessageModel.CLUSTERING);

        consumer.subscribe(MQ_TOPIC,MQ_TAG);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        Random random = new Random();

        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);


                for (MessageExt msg : msgs) {
                    // 可以看到每个queue有唯一的consume线程来消费, 订单对每个queue(分区)有序
                    System.out.println("consumeThread=" + Thread.currentThread().getName() + "queueId=" + msg.getQueueId() + ", content:" + new String(msg.getBody()));
                }

                try {
                    //模拟业务逻辑处理中...
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

    }
}
