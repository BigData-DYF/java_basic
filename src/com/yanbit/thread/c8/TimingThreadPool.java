package com.yanbit.thread.c8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
*	@author yanbit
* @date Aug 25, 2015 3:57:16 PM
*
*/
public class TimingThreadPool extends ThreadPoolExecutor{

  public TimingThreadPool(int corePoolSize, int maximumPoolSize,
      long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }
  
  private final ThreadLocal<Long> startTime= new ThreadLocal<Long>();
  
  private final AtomicLong numTasks = new AtomicLong();
  private final AtomicLong totalTime = new AtomicLong();
  
  @Override
  protected void beforeExecute(Thread t, Runnable r) {
    super.beforeExecute(t, r);
    startTime.set(System.nanoTime());
  }
  
  @Override
  protected void afterExecute(Runnable r, Throwable t) {
    super.afterExecute(r, t);
    long endTime = System.nanoTime();
    long taskTime = endTime - startTime.get();
    numTasks.incrementAndGet();
    totalTime.addAndGet(taskTime);
  }
  
  @Override
  protected void terminated() {
    super.terminated();
  }

}
