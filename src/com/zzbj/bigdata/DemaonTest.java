package com.zzbj.bigdata;

public class DemaonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("���߳̿�ʼ��");
		/*
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("�߳̿�ʼ��");
				while(true){
					
				}
			}
		}).start();
	*/
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("�߳̿�ʼ��");
				while(true){
					
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

}
