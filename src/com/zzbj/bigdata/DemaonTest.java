package com.zzbj.bigdata;

public class DemaonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("主线程开始了");
		/*
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("线程开始了");
				while(true){
					
				}
			}
		}).start();
	*/
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("线程开始了");
				while(true){
					
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

}
