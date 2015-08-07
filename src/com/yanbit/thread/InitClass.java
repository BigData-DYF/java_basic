package com.yanbit.thread;

import java.util.Random;

public class InitClass {
	private int n;
	public InitClass(int n) {
		this.n=n;
	}
	
	public void assertMethod(){
		if (n!=n) {
			throw new AssertionError("this is failure");
		}
	}
	
	public static void main(String[] args) {
		final InitClass in = new InitClass(new Random().nextInt(10));
		int count =0;
		while (true) {
			System.out.println("count:"+count++);
			new Thread(){
				@Override
				public void run() {
					in.assertMethod();
				}
			}.start();
		}
		
	}
}
