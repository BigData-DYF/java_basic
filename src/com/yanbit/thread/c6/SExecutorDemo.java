package com.yanbit.thread.c6;

import java.util.concurrent.Executor;

/**
*	@author yanbit
* @date Aug 21, 2015 10:22:35 AM
*
*/
public class SExecutorDemo implements Executor{

  @Override
  public void execute(Runnable command) {
    new Thread(command).run();
  }
}
