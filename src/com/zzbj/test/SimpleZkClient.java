package com.zzbj.test;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

public class SimpleZkClient {
	private static final String connectString = "192.168.187.200:2181,192.168.187.201:2181,192.168.187.202:2181";
	private static final int sessionTimeout = 3000;
	ZooKeeper zooclientKeeper = null;

	@Before
	public void init() throws IOException {
		zooclientKeeper = new ZooKeeper(connectString, sessionTimeout,
				new Watcher() {
					@Override
					public void process(WatchedEvent event) {
						// TODO Auto-generated method stub
						System.out.println(event.getType() + "---"
								+ event.getPath());
						try {
							zooclientKeeper.getChildren("/", true);
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
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void testCreate() throws KeeperException, InterruptedException {
		if (zooclientKeeper.exists("/ecplise", false) == null) {
			String nodecreateString = zooclientKeeper.create("/ecplise",
					"hello,world".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
		} else {
			System.out.println("/eclipse have exists");
		}
	}

	/******
	 * 获得节点
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void getChildren() throws KeeperException, InterruptedException {
		List<String> childrensList = zooclientKeeper.getChildren("/", true);
		for (String child : childrensList) {
			System.out.println(child);
		}
		Thread.sleep(Long.MAX_VALUE);
	}

	/******
	 * 获得节点的数据
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void getData() throws KeeperException, InterruptedException {
		byte[] data = zooclientKeeper.getData("/ecplise", true, null);
		System.out.println(new String(data));
	}

	/********
	 * 删除节点
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void deleteData() throws KeeperException, InterruptedException {
		zooclientKeeper.delete("/ecplise", -1);
	}
	/********
	 * 删除节点
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void setData() throws KeeperException, InterruptedException {
		zooclientKeeper.setData("/ecplise", "zhuzx".getBytes(), -1);
	}
}
