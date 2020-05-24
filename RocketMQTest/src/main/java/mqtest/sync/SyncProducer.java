package mqtest.sync;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * 可靠性同步地发送方式 : 重要的消息通知，短信通知
 * */
public class SyncProducer {
    private static final String MQ_GROUP_SEND_MSG = "Send_Msg";
//    private static final String MQ_TOPIC = "TOPIC_ORDER_MSG_SEND";
    private static final String MQ_TOPIC = "My-DefaultCluster";
    private static final String MQ_TAG = "Tag_A";
    private static final String NAME_SERVER_ADD = "192.168.47.131:9876;192.168.47.132:9876";

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 1. 创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer(MQ_GROUP_SEND_MSG);

        // 设置 nameServer 地址
        producer.setNamesrvAddr(NAME_SERVER_ADD);
        producer.setVipChannelEnabled(false);//3.2.6的版本没有该设置，在更新或者最新的版本中务必将其设置为false，否则会有问题

        /*
            private String producerGroup;
            private String createTopicKey;
            private volatile int defaultTopicQueueNums;
            private int sendMsgTimeout;
            private int compressMsgBodyOverHowmuch;
            private int retryTimesWhenSendFailed;
            private int retryTimesWhenSendAsyncFailed;
            private boolean retryAnotherBrokerWhenNotStoreOK;
            private int maxMessageSize;
        */

        // 消息发送
//        producer.setCreateTopicKey(MQ_TOPIC_KEY);
        producer.start();
        for (int i = 1; i <= 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message(MQ_TOPIC,MQ_TAG,("Hello RocketMQ - " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }

        // 关闭资源
        producer.shutdown();
    }
}
