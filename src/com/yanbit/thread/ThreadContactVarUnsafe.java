package com.yanbit.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanbit
 *
 *         var contact each other
 */
public class ThreadContactVarUnsafe {
	private final static AtomicInteger lower = new AtomicInteger(0);
	private final static AtomicInteger upper = new AtomicInteger(0);

	public void setLower(int value) {
		if (value > upper.get()) { // check and done not safe
			System.out.println("error");
		}
	}

	public void setUpper(int value) {
		if (value < lower.get()) { // check and done not safe
			System.out.println("error");
		}
	}
}
