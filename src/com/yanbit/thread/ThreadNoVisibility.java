package com.yanbit.thread;

/**
 * @author yanbit
 *
 * number and ready no visibility
 *
 */
public class ThreadNoVisibility {
	private static boolean ready;
	private static int number;
	
	public static void main(String[] args) {
		new ReaderThread().start();
		number=42;
		ready=true;
		System.out.println("----------------");
	}
	private static class ReaderThread extends Thread{
		public void run(){
			while (!ready) {
				Thread.yield();
				System.out.println(number);
			}
		}
	}
}
