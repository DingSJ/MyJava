package mqtest.oneway;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class OnewayProducer {
    private static final String MQ_GROUP_SEND_MSG = "Send_Msg";
        private static final String MQ_TOPIC = "TOPIC_ORDER_MSG_SEND";
//    private static final String MQ_TOPIC = "My-DefaultCluster";
    private static final String MQ_TAG = "Tag_B";
    private static final String NAME_SERVER_ADD = "192.168.47.131:9876;192.168.47.132:9876";

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 1. 创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer(MQ_GROUP_SEND_MSG);

        // 设置 nameServer 地址
        producer.setNamesrvAddr(NAME_SERVER_ADD);


        producer.start();
        for (int i = 1; i <= 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message(MQ_TOPIC,MQ_TAG,("Hello RocketMQ - " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(msg);

            TimeUnit.SECONDS.sleep(1);
        }

        // 关闭资源
        producer.shutdown();
    }
}
