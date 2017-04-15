package com.zzbj.bigdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/***
 * 客户端
 * 
 * @author huijun
 * 
 */
public class DistributedClient {
	private static final String connectString = "192.168.187.200:2181,192.168.187.201:2181,192.168.187.202:2181";
	private static final int sessionTimeout = 4000;
	private static final String PARENTNODE = "/servers";
	private ZooKeeper zk = null;
	private volatile List<String> serverList;// 不会被每个线程copy走了

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
					getServerList();
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

	/***
	 * 获得服务器的列表并监听父节点
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void getServerList() throws KeeperException, InterruptedException {
		List<String> children = zk.getChildren(PARENTNODE, true);
		List<String> servers = new ArrayList<String>();
		for (String child : children) {
			byte[] data = zk.getData(PARENTNODE + "/" + child, false, null);
			servers.add(new String(data));
		}
		serverList = servers;
		//打印服务列表
		System.out.println(serverList);
	}
	/****
	 * 业务逻辑
	 * 
	 * @param hostname
	 * @throws InterruptedException
	 */
	public void handleBussiness() throws InterruptedException {
		System.out.println( "client start working ...");
		Thread.sleep(Long.MAX_VALUE);
	}
	/***
	 * 主线程
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 */
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		DistributedClient client = new DistributedClient();
		client.getConn();
		client.getServerList();
		//业务逻辑
		client.handleBussiness();
		
	}
}
