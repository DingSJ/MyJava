package mqtest.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

public class PushConsumer {
    private static final String MQ_GROUP_SEND_MSG = "Send_Msg";
//    private static final String MQ_TOPIC = "TOPIC_ORDER_MSG_SEND";
        private static final String MQ_TOPIC = "My-DefaultCluster";
    private static final String MQ_TAG = "Tag_B";
    private static final String NAME_SERVER_ADD = "192.168.47.131:9876;192.168.47.132:9876";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQ_GROUP_SEND_MSG);

        consumer.setNamesrvAddr(NAME_SERVER_ADD);

        consumer.subscribe(MQ_TOPIC, "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {

            for (MessageExt msg : msgs) {
                System.out.println("------------------------------");
                System.out.println("getMsgId ： " + msg.getMsgId());
                System.out.println("getQueueId ： " + msg.getQueueId());
                System.out.println("消息体： " + new String(msg.getBody()));
                System.out.println("getTopic ： " + msg.getTopic());
                System.out.println("getTags ： " + msg.getTags());
                System.out.println("getKeys ： " + msg.getKeys());
                System.out.println("getSysFlag ： " + msg.getSysFlag());
                System.out.println("------------------------------");
            }

            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        // 启动消费者实例
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
