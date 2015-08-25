package com.yanbit.thread.c7;
/**
*	@author yanbit
* @date Aug 24, 2015 11:17:50 AM
*
*/
public class InterruptDemo {
  public static void main(String[] args) {
    // zhongduan
    Thread.currentThread().interrupt();
    // if zd
    System.out.println(Thread.currentThread().isInterrupted());
    // cleaner zd status
    System.out.println(Thread.currentThread().interrupted());
    // false
    System.out.println(Thread.currentThread().interrupted());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
