package com.zzbj_01.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTryLock {
	private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
	static Lock lock = new ReentrantLock();

	public static <E> void main(String[] args) {
		new Thread() {
			public void run() {
				Thread thread = Thread.currentThread();
				boolean trylock = lock.tryLock();
				System.out.println(thread.getName() + " " + trylock);
				if (trylock) {
					try {
						System.out.println(thread.getName() + "å¾—åˆ°äº†é”");
						for (int i = 0; i < 5; i++) {
							arrayList.add(i);
						}
						System.out.println(arrayList);
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						System.out.println(thread.getName() + "ÊÍ·ÅÁËËø");
						lock.unlock();
					}
				}

			};
		}.start();

		new Thread() {
			public void run() {
				Thread thread = Thread.currentThread();
				boolean trylock = lock.tryLock();
				System.out.println(thread.getName() + " " + trylock);
				if (trylock) {
					try {
						System.out.println(thread.getName() + "ï¿½Ãµï¿½ï¿½ï¿½ï¿½ï¿½");
						for (int i = 0; i < 5; i++) {
							arrayList.add(i);
						}
						System.out.println(arrayList);
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						System.out.println(thread.getName() + "ÊÍ·ÅÁËËø");
						lock.unlock();
					}
				}

			};
		}.start();

	}
}
