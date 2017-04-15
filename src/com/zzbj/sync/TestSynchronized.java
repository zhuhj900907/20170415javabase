package com.zzbj.sync;

/***
 * ͬ����
 * 
 * @author huijun
 * 
 */
public class TestSynchronized {
	public static void main(String[] args) {
		final TestSynchronized testSynchronized1 = new TestSynchronized();
		final TestSynchronized testSynchronized2 = new TestSynchronized();
		new Thread("thread1") {
			@Override
			public void run() {
				synchronized (testSynchronized1) {
					try {
						System.out.println(this.getName() + ">>>>>>>start");
						Thread.sleep(5000);
						System.out.println(this.getName() + ">>>>>>>����");
						System.out.println(this.getName() + ">>>>>>>end");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}.start();
		
		
		
		new Thread("thread2") {
			@Override
			public void run() {
				synchronized (testSynchronized2) {//����ͬһ����
					try {
						System.out.println(this.getName() + ">>>>>>>start");
						Thread.sleep(1000);
						System.out.println(this.getName() + ">>>>>>>����");
						System.out.println(this.getName() + ">>>>>>>end");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}.start();

	}
}
