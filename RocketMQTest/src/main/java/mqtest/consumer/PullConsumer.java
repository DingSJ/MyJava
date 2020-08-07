package mqtest.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.PullResultExt;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * # 是否允许 Broker 自动创建Topic
 * autoCreateTopicEnable=false
 * # 是否允许 Broker 自动创建订阅组
 * autoCreateSubscriptionGroup=false
 *
 * 需要人工创建生产Topic 和 消息组
 * */
public class PullConsumer {
    private static final String MQ_GROUP_SEND_MSG = "Send_Msg";
    //    private static final String MQ_TOPIC = "TOPIC_ORDER_MSG_SEND";
    private static final String MQ_TOPIC = "TOPIC_ORDER_MSG_SEND";
    private static final String MQ_TAG = "Tag_B";
    private static final String NAME_SERVER_ADD = "192.168.47.131:9876;192.168.47.132:9876";
    private static final Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        offsetTable.clear();
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(MQ_GROUP_SEND_MSG);

        consumer.setNamesrvAddr(NAME_SERVER_ADD);

        // 启动消费者实例
        consumer.start();


        // 注册回调实现类来处理从broker拉取回来的消息
        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues(MQ_TOPIC);
        for (MessageQueue mq : messageQueues) {
            // System.out.println("Consume from the queue: " + mq);
            System.out.println("当前获取的消息的归属队列是: " + mq.getQueueId());

            PullResultExt pullResult = (PullResultExt) consumer.pullBlockIfNotFound(mq, null,
                    getMessageQueueOffset(mq), 32);
            putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
            switch (pullResult.getPullStatus()) {

                case FOUND:
                    List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                    for (MessageExt m : messageExtList) {
                        System.out.println("收到了消息:" + new String(m.getBody()) + " --- TAG: " + m.getTags() +
                                " --- Topic : " + m.getTopic());
                    }
                    break;

                case NO_MATCHED_MSG:
                    break;

                case NO_NEW_MSG:
                    break;

                case OFFSET_ILLEGAL:
                    break;

                default:
                    break;
            }
        }
    }
    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offsetTable.get(mq);
        if (offset != null){
            return offset;
        }
        return 0;
    }
}
