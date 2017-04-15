package com.zzbj.bigdata;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.runner.Describable;

public class DistributedServer {
	private static final String connectString = "192.168.187.200:2181,192.168.187.201:2181,192.168.187.202:2181";
	private static final int sessionTimeout = 4000;
	private static final String PARENTNODE = "/servers";
	private ZooKeeper zk = null;

	/***
	 * 创建客户端连接
	 * 
	 * @throws IOException
	 */
	public void getConn() throws IOException {
		zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getType() + "---" + event.getPath());
				try {
					zk.getChildren("/", true);
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/****
	 * 创建节点
	 * 
	 * @param hostname
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void registerServer(String hostname) throws KeeperException,
			InterruptedException {
		String create = zk.create(PARENTNODE + "/server", hostname.getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(hostname + "is online..." + create);

	}

	/****
	 * 业务逻辑
	 * 
	 * @param hostname
	 * @throws InterruptedException
	 */
	public void handleBussiness(String hostname) throws InterruptedException {
		System.out.println(hostname + "start working ...");
		Thread.sleep(Long.MAX_VALUE);
	}

	/****
	 * 主启动函数
	 * 
	 * @param args
	 * @throws IOException
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, KeeperException,
			InterruptedException {
		// 获取zk链接
		DistributedServer ds = new DistributedServer();
		ds.getConn();
		//
		ds.registerServer(args[0]);
		//
		ds.handleBussiness(args[0]);
	}
}
