package com.yanbit.thread;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import com.yanbit.anno.Unsafe;

public class ThreadStateSafe {

	/**
	 * atomic class
	 */
	private static AtomicLong count = new AtomicLong();

	/**
	 * count share state
	 * 
	 * @param value
	 * @return
	 */
	@Unsafe
	public static Integer addOne(Integer value) {
		count.incrementAndGet();
		System.out.println("count:" + count);
		return value + 1;
	}

	public static void main(String[] args) {
		final Random r = new Random();
		Thread one = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("one:" + addOne(r.nextInt(10)));
				}
			}
		};

		Thread two = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("two:" + addOne(r.nextInt(10)));
				}

			}
		};
		one.start();
		two.start();
	}
}
