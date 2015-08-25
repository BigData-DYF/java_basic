package com.yanbit.thread.c6;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author yanbit
 * @date Aug 21, 2015 11:20:53 AM
 *
 */
public class CallableDemo {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService service = Executors.newSingleThreadExecutor();
    Future f = service.submit(new Callable<Integer>(
        ) {
          @Override
          public Integer call() throws Exception {
            return new Random().nextInt(10);
          }
    });
    System.out.println(f.get());
  }
}
