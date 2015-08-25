package com.yanbit.thread.c6;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yanbit
 * @date Aug 21, 2015 11:20:53 AM
 *
 */
public class CallableDemo3 {
  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newCachedThreadPool();  
    CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);  
    for(int i = 1; i < 5; i++) {  
        final int taskID = i;  
        cs.submit(new Callable<Integer>() {  
            public Integer call() throws Exception {
              TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                return taskID;  
            }  
        });  
    }  
    // 可能做一些事情  
    for(int i = 1; i < 5; i++) {  
        try {  
            System.out.println(cs.take().get());
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } catch (ExecutionException e) {  
            e.printStackTrace();  
        }  
    }  
  }
}
