package mqtest.async;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
 *
 * */
public class AsyncProducer {

    private static final String MQ_GROUP_SEND_MSG = "Send_Msg";
    //    private static final String MQ_TOPIC = "TOPIC_ORDER_MSG_SEND";
    private static final String MQ_TOPIC = "My-DefaultCluster";
    //    private static final String MQ_TOPIC = "my-broker-a";
    private static final String MQ_TAG = "Tag_B";
    private static final String NAME_SERVER_ADD = "192.168.47.131:9876;192.168.47.132:9876";

    public static void main(String[] args) throws MQClientException, InterruptedException {

        // 创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer();

        // 设置 NameServer 地址
        producer.setNamesrvAddr(NAME_SERVER_ADD);

        // 设置发送组
        producer.setProducerGroup(MQ_GROUP_SEND_MSG);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        producer.setVipChannelEnabled(false);//3.2.6的版本没有该设置，在更新或者最新的版本中务必将其设置为false，否则会有问题

        // 消息发送
        CountDownLatch latch = new CountDownLatch(10);
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Message message = null;
            try {
                message = new Message(MQ_TOPIC, MQ_TAG, ("Java-Order-Msg-" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

                // SendCallback接收异步返回结果的回调
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", i,
                                sendResult.getMsgId());
                        latch.countDown();
                    }

                    @Override
                    public void onException(Throwable e) {
                        System.out.printf("%-10d Exception %s %n", i, e);
                        e.printStackTrace();
                        latch.countDown();
                    }
                });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        });

        latch.await(1, TimeUnit.SECONDS);

        // 关闭资源
        producer.shutdown();
    }
}
