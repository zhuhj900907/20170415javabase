package com.zzbj.thread;

import java.util.Random;

/***
 * �̳еķ�ʽʵ���߳�
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
		//���ʹ��run����˳��ִ����,runֻ����ͨ�ķ������ö����ǿ����µ��߳�
		//�����߳�ʹ�õ���start������run 
	}
}
