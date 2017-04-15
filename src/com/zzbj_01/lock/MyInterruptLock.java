package com.zzbj_01.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyInterruptLock {
	private static Lock lock = new ReentrantLock();
	/****
	 * 插入了锁
	 * @param thread
	 * @throws InterruptedException
	 */
	public void insert(Thread thread) throws InterruptedException {
		lock.lockInterruptibly();
		try {
			System.out.println(thread.getName()+"得到了锁...");
			long startTime = System.currentTimeMillis();
			for(;;){
				if(System.currentTimeMillis()-startTime >= Integer.MAX_VALUE){
					break;
				}
			}
		} finally{
			System.out.println(thread.getName()+"执行了finally...");
			lock.unlock();
			System.out.println(thread.getName()+"释放了锁...");
		}
	}
	public static void main(String[] args) {
		MyInterruptLock test = new MyInterruptLock();
		MyThread thread1 = new MyThread(test);
		MyThread thread2 = new MyThread(test);
		thread1.start();
		thread2.start();
		try{
			Thread.sleep(2000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.interrupt();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
	}
}
class MyThread extends Thread{
	private MyInterruptLock test = null;
	public MyThread(MyInterruptLock test){
		this.test = test;
	}
	@Override
	public void run() {
		try {
			test.insert(Thread.currentThread());
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName()+"被中断了....");
		}
	}
}
