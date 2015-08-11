package com.yanbit.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yanbit
 *
 *	pack shared status
 *
 */
public class ThreadPack {
	// status
	private final Set<Object> set = new HashSet<>();
	
	public synchronized void addObj(Object obj){
		set.add(obj);
	}
	
	public synchronized void containObj(Object obj){
		set.contains(obj);
	}
	
}
