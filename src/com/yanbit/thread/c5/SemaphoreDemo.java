package com.yanbit.thread.c5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
*	@author yanbit
* @date Aug 20, 2015 11:09:49 AM
*
*/
public class SemaphoreDemo {
  private static BoundedHashSet set = new BoundedHashSet(5);
  private static CountDownLatch count = new CountDownLatch(3);
  public static void main(String[] args) throws InterruptedException {
    Thread a = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(1);
          set.add(1);
          set.add(2);
          set.add(3);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        count.countDown();
      }
    });
    Thread b = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(1);
          set.add(4);
          set.add(5);
          set.add(6);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        count.countDown();
      }
    });
    Thread c = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(5);
          set.remove(4);
          System.out.println("reomove 4");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        count.countDown();
      }
    });
    a.start();
    b.start();
    c.start();
    count.await();
    System.out.println(set);
  }
}

class BoundedHashSet<T>{
  
  private final Set<T> set;
  private final Semaphore sem;
  
  public BoundedHashSet( int bound) {
    super();
    this.set = Collections.synchronizedSet(new HashSet<T>());
    this.sem = new Semaphore(bound);
  }
  
  // 持有凭证
  public boolean add(T o) throws InterruptedException{
    sem.acquire();
    boolean wasAdded=false;
    try {
      wasAdded=set.add(o);
      System.out.println("set:"+o);
      return wasAdded;
    } finally  {
      if (!wasAdded) {
        sem.release();
      }
    }
  }
  // 释放凭证
  public boolean remove(Object o){
    boolean wasRemoved = set.remove(o);
    System.out.println("remove:"+o);
    if (wasRemoved) {
      sem.release();
    }
    return wasRemoved;
  }
  
}
