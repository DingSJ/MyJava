package zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class MyWatcher implements Watcher {
    private CountDownLatch latch;
    private ZooKeeper zooKeeper;
    private Stat stat = new Stat();

    public MyWatcher(CountDownLatch latch, ZooKeeper zooKeeper) {
        this.latch = latch;
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Watcher.Event.EventType.None == event.getType() && null == event.getPath()) {
                latch.countDown();
                //zk目录节点数据变化通知事件
            } else if (event.getType() == Watcher.Event.EventType.NodeDataChanged) {
                try {
                    System.out.println("NodeDataChanged >> " + zooKeeper.getData(event.getPath(),true, stat));
                } catch (Exception e) {
                }
            } else if (event.getType() == Event.EventType.DataWatchRemoved) {
                try {
                    System.out.println("DataWatchRemoved >> " + zooKeeper.exists(event.getPath(),true));
                } catch (Exception e) {
                }
            } else if (event.getType() == Event.EventType.NodeCreated) {
                try {
                    System.out.println("NodeCreated >> " + zooKeeper.getData(event.getPath(),true, stat));
                } catch (Exception e) {
                }
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("NodeChildrenChanged" + JSON.toJSONString(zooKeeper.getChildren(event.getPath(),true, stat)));
                } catch (Exception e) {
                }
            }
        }
    }
}
