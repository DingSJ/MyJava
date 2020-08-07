package zookeeper;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import sun.plugin2.message.JavaScriptBaseMessage;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Master2{
    private static ZooKeeper zooKeeper;
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {

        String connUrl = "192.168.47.132:2181,192.168.47.132:2182,192.168.47.132:2183";

        // 执行完本行代码，zookeeper不一定会处于已连接状态
        zooKeeper = new ZooKeeper(connUrl, 15_000, new MyWatcher(latch, zooKeeper));

        latch.await();

        try {
            System.out.println("/test exists: " + JSON.toJSONString(zooKeeper.getData("/test", true, new Stat())));
            System.out.println("NodeChildrenChanged" + JSON.toJSONString(zooKeeper.getChildren("/test",true)));
        } catch (KeeperException e) {
            e.printStackTrace();
        }

        Thread.sleep(300_000);
        zooKeeper.close();
    }

}
