package zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorDemo {
    public static void main(String[] args) throws Exception {
        String connNode = "192.168.47.132:2181,192.168.47.132:2182,192.168.47.132:2183";

        CuratorFramework zkc = CuratorFrameworkFactory.newClient(connNode, new RetryPolicy() {
            @Override
            public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                return true;
            }
        });

        zkc.start();

        String path = "/test-3";
        String s = zkc.create().withMode(CreateMode.PERSISTENT).forPath(path);
        System.out.println("创建： " + s);
        Stat stat = zkc.checkExists().watched().forPath(path);
        System.out.println("stat ： " + JSON.toJSONString(stat));

        System.out.println(new String(zkc.getData().watched().forPath(path)));

        zkc.close();
    }
}
