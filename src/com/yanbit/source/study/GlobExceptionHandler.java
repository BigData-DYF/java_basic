package com.yanbit.source.study;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @author yanbit
 *
 */
public class GlobExceptionHandler {
  public static void main(String[] args) {
    Thread t = new Thread(new AdminThread());
    t.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    t.start();
  }
}

class AdminThread implements Runnable {
  @Override
  public void run() {
    try {
      Thread.sleep(2000);
      System.out.println("start ... Exception");
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    throw new NullPointerException();
  }
}

class ExceptionHandler implements UncaughtExceptionHandler {

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println("Thread:" + t + " Exception message:" + e);
  }
}