package com.zzbj.bigdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/***
 * �ͻ���
 * 
 * @author huijun
 * 
 */
public class DistributedClient {
	private static final String connectString = "192.168.187.200:2181,192.168.187.201:2181,192.168.187.202:2181";
	private static final int sessionTimeout = 4000;
	private static final String PARENTNODE = "/servers";
	private ZooKeeper zk = null;
	private volatile List<String> serverList;// ���ᱻÿ���߳�copy����

	/***
	 * �����ͻ�������
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
	 * ��÷��������б��������ڵ�
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
		//��ӡ�����б�
		System.out.println(serverList);
	}
	/****
	 * ҵ���߼�
	 * 
	 * @param hostname
	 * @throws InterruptedException
	 */
	public void handleBussiness() throws InterruptedException {
		System.out.println( "client start working ...");
		Thread.sleep(Long.MAX_VALUE);
	}
	/***
	 * ���߳�
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
		//ҵ���߼�
		client.handleBussiness();
		
	}
}
