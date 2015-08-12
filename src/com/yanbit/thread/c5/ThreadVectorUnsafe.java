package com.yanbit.thread.c5;

import java.util.Vector;

/**
 * @author yanbit
 * 
 *         client process get and del error
 *
 *         vector safe ,but opertaion failure
 */
public class ThreadVectorUnsafe {
	public static Object getLast(Vector list) {
		int lastIndex = list.size() - 1;
		return list.get(lastIndex);
	}

	public static Object deleteLast(Vector list) {
		int lastIndex = list.size() - 1;
		return list.remove(lastIndex);
	}
}
