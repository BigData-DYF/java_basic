package com.yanbit.thread;

import java.util.Random;

import com.yanbit.anno.safe;

public class ThreadStateless {
	
	/**
	 * Stateless method Thread safe
	 * 
	 * @param value Thread params
	 * @return
	 */
	@safe
	public static Integer addOne(Integer value) {
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
