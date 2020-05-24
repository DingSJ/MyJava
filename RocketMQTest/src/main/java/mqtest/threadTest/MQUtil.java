package mqtest.threadTest;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MQUtil {

    private static final String MQ_GROUP_SEND_MSG = "Send_Msg";
    private static final String MQ_TOPIC = "My-DefaultCluster-Order";
    private static final String MQ_TAG = "Tag_B";
    private static final String NAME_SERVER_ADD = "192.168.47.131:9876;192.168.47.132:9876";

    private static DefaultMQProducer producer;

    public MQUtil(){
        producer = new DefaultMQProducer(MQ_GROUP_SEND_MSG);
        producer.setNamesrvAddr(NAME_SERVER_ADD);
        producer.setCreateTopicKey(MQ_TOPIC);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        producer.setRetryTimesWhenSendAsyncFailed(0);
        producer.setVipChannelEnabled(false);

    }

    public SendResult sendMessgge(String message, String threadName, long orderId) {
        Message msg;
        SendResult send = null;
        try {
            msg = new Message(MQ_TOPIC, MQ_TAG, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            send = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    long argVal = (Long) arg;
                    Long index = argVal % mqs.size();
                    System.out.println(threadName + arg + " - " + "选择队列：" + index);
                    return mqs.get(index.intValue());
                }
            }, orderId);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
        return send;
    }

    public void close(){
        producer.shutdown();
        System.out.println("MQ 资源关闭");
    }
}
