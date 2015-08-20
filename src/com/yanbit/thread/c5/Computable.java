package com.yanbit.thread.c5;
/**
*	@author yanbit
* @date Aug 20, 2015 2:53:17 PM
*
*/
public interface Computable<A,V> {
  V compute(A arg) throws InterruptedException;
}
