package com.yanbit.thread.c5;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yanbit
 * @date Aug 20, 2015 10:16:03 AM
 *
 */
public class FutureTaskDemo {
  public static void main(String[] args)
      throws InterruptedException, ExecutionException {
    Random rtime = new Random();
    Call c1 = new Call(4);
    Call c2 = new Call(5);
    FutureTask<String> futureTask1 = new FutureTask<String>(c1);
    FutureTask<String> futureTask2 = new FutureTask<String>(c2);
    ExecutorService executor = Executors.newCachedThreadPool();
    try {
      executor.execute(futureTask1);
      executor.execute(futureTask2);
      System.out.println(futureTask1.isDone());
      System.out.println(futureTask2.isDone());
      // System.out.println(futureTask1.cancel(true));
      // System.out.println(futureTask1.isCancelled());
      System.out.println("f1:"+futureTask1.get(1, TimeUnit.MINUTES));
      
      
    } catch (TimeoutException e) {
      System.out.println("+++++++++");
      e.printStackTrace();
    }finally{
      System.out.println("f2:"+futureTask2.get());
      //executor.shutdown();
    }
  }
}

class Call implements Callable<String> {
  private long waitTime;

  public Call(long waitTime) {
    super();
    this.waitTime = waitTime;
  }

  @Override
  public String call() throws Exception {
    TimeUnit.SECONDS.sleep(waitTime);
    return Thread.currentThread().getName();
  }

}
