package com.yanbit.thread.c5;

import java.util.Vector;

/**
 * @author yanbit
 * 
 *         client lock
 */
public class ThreadVectorSafe {
	public static Object getLast(Vector list) {
		synchronized (list) {
			int lastIndex = list.size() - 1;
			return list.get(lastIndex);

		}
	}

	public static Object deleteLast(Vector list) {
		synchronized (list) {
			int lastIndex = list.size() - 1;
			return list.remove(lastIndex);
		}
	}

	public static void forEach(Vector list) {
		synchronized (list) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
}
