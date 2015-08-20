package com.yanbit.thread.c5;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
*	@author yanbit
* @date Aug 20, 2015 2:20:49 PM
*
*/
public class CyclicBarrierDemo {
  
  public static void main(String[] args) {
    CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
      @Override
      public void run() {
        System.out.println("all condition arrived!");
      }
    });
    
    for (int i = 0; i < 3; i++) {
      new Thread(new Worker(cb, String.valueOf(i))).start();
    }
  }

}

class Worker implements Runnable{

  CyclicBarrier barrier;
  String name ;
  
  public Worker(CyclicBarrier barrier, String name) {
    super();
    this.barrier = barrier;
    this.name = name;
  }

  @Override
  public void run() {
    System.out.println(name+": "
        + " waiting");
    try {
      TimeUnit.SECONDS.sleep(new Random().nextInt(10));
      barrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
    System.out.println(name+": working");
  }
  
}