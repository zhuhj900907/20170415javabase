package com.zzbj.thread;

public class ThreadRunnable implements Runnable {
	private int x;
	public  ThreadRunnable(int x) {
		this.x = x;
	}
	@Override
	public void run() {
		String tname  = Thread.currentThread().getName();
		System.out.println("线程"+tname+"的方法被调用了....");
		for (int i = 0; i < 20; i++) {
			System.out.println("x:"+x);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	/*******
	 * 主程序
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread1 = new Thread(new ThreadRunnable(1),"thread-1");
		Thread thread2 = new Thread(new ThreadRunnable(2),"thread-2");
		Thread thread3 = new Thread(new ThreadRunnable(3),"thread-3");
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		/*
		thread1.run();
		thread2.run();
		thread3.run();
		*/
	}
}
