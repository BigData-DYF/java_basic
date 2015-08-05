package com.yanbit.thread;

import com.yanbit.anno.Unsafe;

public class LazyInitRace {

	public  Object obj;
	
	@Unsafe
	public  Object getInstance(){
		if (obj==null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			obj = new Object();
		}
		return obj;
	}
	
	public static void main(String[] args) {
		final LazyInitRace race = new LazyInitRace();
		Thread one = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("one:" + race.getInstance());
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
					System.out.println("two:" + race.getInstance());
				}

			}
		};
		one.start();
		two.start();
	}
}
