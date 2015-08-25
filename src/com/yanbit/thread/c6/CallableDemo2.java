package com.yanbit.thread.c6;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author yanbit
 * @date Aug 21, 2015 11:20:53 AM
 *
 */
public class CallableDemo2 {
  public static void main(String[] args) {
    Callable<Integer> call = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        return new Random().nextInt(10);
      }
    };
    FutureTask<Integer> future = new FutureTask<Integer>(call);
    new Thread(future).start();
    try {
      Thread.sleep(2000);
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
