package com.yanbit.thread.c5;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yanbit
 * @date Aug 19, 2015 6:21:32 PM
 *
 */
public class CountDownLatchDemo {
  public static void main(String[] args) {
    CountDownLatch latch = new CountDownLatch(3);
    ExecutorService executor = Executors.newCachedThreadPool();
    executor.execute(new worker("yan", latch));
    executor.execute(new worker("luo", latch));
    executor.execute(new worker("hong", latch));
    executor.execute(new Boss(latch));
    executor.shutdown();
  }
}

class worker implements Runnable {
  String name;
  CountDownLatch latch;

  public worker(String name, CountDownLatch latch) {
    super();
    this.name = name;
    this.latch = latch;
  }

  @Override
  public void run() {
    System.out.println(name + ":准备出发...");
    try {
      TimeUnit.SECONDS.sleep(new Random().nextInt(10));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(name + ":已经到餐厅了...");
    this.latch.countDown();
  }
}

class Boss implements Runnable {
  CountDownLatch latch;

  public Boss(CountDownLatch latch) {
    super();
    this.latch = latch;
  }

  @Override
  public void run() {
    System.out.println("fang2:等你们吃饭昂...");
    try {
      this.latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("fang2:人到齐了，来时吃饭啦....");
  }

}
