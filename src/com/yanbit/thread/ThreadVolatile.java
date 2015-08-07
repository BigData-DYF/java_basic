package com.yanbit.thread;

/**
 * @author yanbit
 *
 *	visibility
 *
 */
public class ThreadVolatile {

	private volatile static boolean running = true;

	public void count() {
		int count = 0;
		while (running) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
			System.out.println("count :" + count);
		}
	}
	
	public static void main(String[] args) {
		
		new Thread(){
			@Override
			public void run() {
				try {
					System.out.println("start sleep ...");
					Thread.sleep(3000);
					running = false;
					System.out.println("finish sleep .");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();;
		new ThreadVolatile().count();
	}

}
