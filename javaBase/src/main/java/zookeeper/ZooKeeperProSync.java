package zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式配置中心demo
 * @author 
 *
 */
public class ZooKeeperProSync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        //zookeeper配置数据存放路径
        String path = "/test";

        //连接zookeeper并且注册一个默认的监听器
        String connNode = "192.168.47.132:2181,192.168.47.132:2182,192.168.47.132:2183";

        zk = new ZooKeeper(connNode, 5000, new ZooKeeperProSync());

        //等待zk连接成功的通知
        connectedSemaphore.await();


        //获取path目录节点的配置数据，并注册默认的监听器
        System.out.println(new String(zk.getData(path, true, stat)));
        List<String> children = zk.getChildren(path, true, stat);
        System.out.println(JSON.toJSONString(children));

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        //zk连接成功通知事件
        if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
            if (Watcher.Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
                //zk目录节点数据变化通知事件
            } else if (event.getType() == Watcher.Event.EventType.NodeDataChanged) {
                try {
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                try {
                    System.out.println("配置已删除" + event.getPath());
                } catch (Exception e) {
                }
            } else if (event.getType() == Event.EventType.NodeCreated) {
                try {
                    System.out.println("新增配置：" + event.getPath() + " ==>> " + new String(zk.getData(event.getPath(), true, stat)));
                } catch (Exception e) {
                }
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("配置数量变更");
                    List<String> children = zk.getChildren(event.getPath(), true, stat);
                    for (String c : children) {
                        System.out.println(event.getPath() + "/" + c + " : " + new String(zk.getData(event.getPath() + "/" + c, true, stat)));
                    }
                } catch (Exception e) {
                }
            }
        }

    }
}