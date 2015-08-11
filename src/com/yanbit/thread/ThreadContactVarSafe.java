package com.yanbit.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanbit
 *
 *         var contact each other
 */
public class ThreadContactVarSafe {
	private final static AtomicInteger lower = new AtomicInteger(0);
	private final static AtomicInteger upper = new AtomicInteger(0);

	public synchronized void setLower(int value) {
		if (value > upper.get()) { // check and done not safe
			System.out.println("error");
		}
	}

	public synchronized void setUpper(int value) {
		if (value < lower.get()) { // check and done not safe
			System.out.println("error");
		}
	}
}
