package com.yanbit.thread;

import java.util.Random;

public class ThreadMutable {
	private int value = 0;

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}

	public static void main(String[] args) {
		final Random r = new Random();
		final ThreadMutable t = new ThreadMutable();
		Thread one = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("get:" + t.get());
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
					int value = r.nextInt(10);
					t.set(value);
					System.out.println("set:" + value);
				}

			}
		};
		one.start();
		two.start();
	}
}
