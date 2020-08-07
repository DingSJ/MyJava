package zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Master implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private String connUrl;
    private ZooKeeper zooKeeper;

    public Master(String connUrl) {
        this.connUrl = connUrl;
    }

    private void startZk() throws IOException, KeeperException, InterruptedException {
        zooKeeper = new ZooKeeper(connUrl, 15_000, this);
        System.out.println("INIT SUCC ...");
    }
//
//    private void setData(String path, String value) throws KeeperException, InterruptedException {
//        if (StringUtils.isEmpty(value)) {
//            value = "";
//        }
//        zooKeeper.setData(path,  value.getBytes(),0);
//    }
//
//    private void create(String path, String value) throws KeeperException, InterruptedException {
//        if (StringUtils.isEmpty(value)) {
//            value = "";
//        }
//        zooKeeper.create(path,  value.getBytes(),null, CreateMode.PERSISTENT);
//    }
//
//    private void exists(String path) throws KeeperException, InterruptedException {
//        if (StringUtils.isEmpty(path)) {
//            throw new RuntimeException("path is null");
//        }
//        System.out.println(zooKeeper.exists(path,  false));
//    }
    private void stopZk() throws InterruptedException {
        zooKeeper.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        String connUrl = "192.168.47.132:2181,192.168.47.132:2182,192.168.47.132:2183";

        Master master = new Master(connUrl);

        master.startZk();

        connectedSemaphore.await();

        //获取path目录节点的配置数据，并注册默认的监听器
        System.out.println(master.getData("/test"));

        Thread.sleep(300_000);

        master.stopZk();
    }

    private String getData(String path) throws KeeperException, InterruptedException {
        return new String(zooKeeper.getData(path, true, new Stat()));
    }

    @Override
    public void process(WatchedEvent event) {

        if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
            switch (event.getType()){
                case None:
                    if (null == event.getPath()) {
                        connectedSemaphore.countDown();
                    }
                    break;
                case NodeCreated:
                    System.out.println("NodeCreated ..");
                    break;
                case NodeDeleted:
                    System.out.println("NodeDeleted ..");
                    break;
                case NodeDataChanged:
                    System.out.println("NodeDataChanged ..");
                    break;
                case NodeChildrenChanged:
                    System.out.println("NodeChildrenChanged ..");
                    break;
                default:
                    System.out.println(JSON.toJSONString(event));
                    break;
            }
        }
    }
}
