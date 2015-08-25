package com.yanbit.thread.c6;

import java.util.concurrent.Executor;

/**
*	@author yanbit
* @date Aug 21, 2015 10:20:37 AM
*
*/
public class OExecutorDemo implements Executor{

  @Override
  public void execute(Runnable command) {
     new Thread(command).start();
  }
}
