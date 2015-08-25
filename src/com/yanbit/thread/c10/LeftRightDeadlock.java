package com.yanbit.thread.c10;

import java.util.concurrent.TimeUnit;

/**
 * @author yanbit
 * @date Aug 25, 2015 4:58:24 PM
 *
 */
public class LeftRightDeadlock {
  private static final Object left = new Object();
  private static final Object right = new Object();

  public static void leftRight() throws InterruptedException {
    synchronized (left) {
      TimeUnit.SECONDS.sleep(2);
      System.out.println("------");
      synchronized (right) {
        System.out.println("left right");
      }
    }
  }

  public static void rightLeft() throws InterruptedException {
    synchronized (right) {
      TimeUnit.SECONDS.sleep(2);
      System.out.println("------");
      synchronized (left) {
        System.out.println("right left");
      }
    }
  }

  public static void main(String[] args) {
    Thread t1 = new Thread() {
      @Override
      public void run() {
        try {
          leftRight();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    Thread t2 = new Thread() {
      @Override
      public void run() {
        try {
          rightLeft();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    t1.start();
    t2.start();
  }
}
