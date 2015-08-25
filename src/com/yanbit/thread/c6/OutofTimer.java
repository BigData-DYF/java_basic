package com.yanbit.thread.c6;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
*	@author yanbit
* @date Aug 21, 2015 10:52:05 AM
*
*/
public class OutofTimer {
  public static void main(String[] args) throws InterruptedException {
    Timer timer = new Timer();
    timer.schedule(new Throw(), 1);
    TimeUnit.SECONDS.sleep(1);
    timer.schedule(new Throw(), 5);
    TimeUnit.SECONDS.sleep(5);
  }
}

class Throw extends TimerTask{

  @Override
  public void run() {
    throw new RuntimeException();
  }
  
}
