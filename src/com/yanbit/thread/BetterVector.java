package com.yanbit.thread;

import java.util.Vector;

/**
 * @author yanbit
 *
 * @param <E>
 * 
 * 	extends class functions
 */
public class BetterVector<E> extends Vector<E> {

	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !contains(x);
		if (absent) {
			add(x);
		}
		return absent;
	}
}
