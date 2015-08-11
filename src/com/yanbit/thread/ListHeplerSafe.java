package com.yanbit.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yanbit
 *
 * @param <E>
 * 
 *            use other lock
 */
public class ListHeplerSafe<E> {
	public List<E> list = Collections.synchronizedList(new ArrayList<E>());

	public boolean putIfAbsent(E x) {
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent) {
				list.add(x);
			}
			return absent;
		}

	}
}
