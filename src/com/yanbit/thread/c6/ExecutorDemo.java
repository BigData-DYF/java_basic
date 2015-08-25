package com.yanbit.thread.c6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yanbit
 * @date Aug 21, 2015 10:16:36 AM
 *
 */
public class ExecutorDemo {
  public static void main(String[] args) {
    ExecutorService threadService = Executors.newFixedThreadPool(10);
    threadService.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("hello");
      }
    });
    threadService.shutdown();
  }
}
