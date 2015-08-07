package com.yanbit.thread;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author yanbit
 *
 *	ThreadLocal copy var to local
 */
public class ThreadLocalClose {
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				doSomething();
			}
		};

		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread() + ":" + hi.get());
				}
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread() + ":" + hi.get());
					hi.set("hi hello");
					System.out.println("-------------sleep--------------");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread() + ":" + hi.get());
				}
			}
		}.start();

	}

	/**
	 * local var ,not shared Thread
	 * 
	 */
	public static void doSomething() {
		SortedSet<String> a;
		int number = 0;
		String b = null;

		a = new TreeSet<String>();

		// ... ...

	}

	private static ThreadLocal<String> hi = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "hello";
		}
	};
}
