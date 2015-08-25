package com.yanbit.thread.c7;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author yanbit
 * @date Aug 24, 2015 11:48:38 AM
 *
 *       最重要的就是中断需要程序自己去检测然后做相应的处理，而Thread.stop会直接在代码执行过程中抛出ThreadDeath错误，
 *       这是一个java.lang.Error的子类
 */
public class StopDemo {
  private static final int[] array = new int[80000];
  private static final Thread t = new Thread() {
    public void run() {
      try {
        System.out.println(sort(array));
      } catch (Error err) {
        err.printStackTrace();
      }
      System.out.println("in thread t");
    }
  };

  static {
    Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(i + 1);
    }
  }

  private static int sort(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      for (int j = 0; j < array.length - i - 1; j++) {
        if (array[j] < array[j + 1]) {
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
    return array[0];
  }

  public static void main(String[] args) throws Exception {
    t.start();
    TimeUnit.SECONDS.sleep(1);
    System.out.println("go to stop thread t");
    t.stop();
    System.out.println("finish main");
  }
}
