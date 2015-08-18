package com.yanbit.source.study;
/**
*	@author yanbit
* @date Aug 18, 2015 3:02:01 PM
*
*/
public class SystemExitAndRuntimeHaltDemo {
  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      Thread t = new Thread(new Hook());
      Runtime.getRuntime().addShutdownHook(t);
    }
    //System.exit(1);
    //Runtime.getRuntime().halt(-1);
  }
}

class Hook implements Runnable{
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName());
    try {
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName()+" hook...");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
