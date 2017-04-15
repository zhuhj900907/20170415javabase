package com.zzbj.thread;

import java.util.Random;

/***
 * 继承的方式实现线程
 * 
 * @author huijun
 * 
 */
public class ThreadExtend extends Thread {
	String flag;

	public ThreadExtend(String flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		String tname = Thread.currentThread().getName();
		System.out.println(tname + "   run.........");
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			try {
				sleep(random.nextInt(10)*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(flag+">>>"+i);
		}
	}

	public static void main(String[] args) {
		Thread tt = new ThreadExtend("thread1");
		Thread tt2 = new ThreadExtend("thread2");
		Thread tt3 = new ThreadExtend("thread3");
		Thread tt4 = new ThreadExtend("thread4");
		tt.start();
		tt2.start();
		tt3.start();
		tt4.start();
		//如果使用run就是顺序执行了,run只是普通的方法调用而不是开启新的线程
		//启动线程使用的是start而不是run 
	}
}
