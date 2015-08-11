package com.yanbit.thread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yanbit
 *
 *	Thread var safe
 */
public class ThreadVar {
	private static List<String> key1 = new CopyOnWriteArrayList<>();
	private static List<String> key2 = new CopyOnWriteArrayList<>();

	public void removekey1(String value1) {
		key1.remove(value1);
	}

	public void removekey2(String value2) {
		key2.remove(value2);
	}

	public void addkey1(String value1) {
		key1.add(value1);
	}

	public void addkey2(String value2) {
		key2.add(value2);
	}
}
