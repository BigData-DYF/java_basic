package com.yanbit.thread.c5;

import java.math.BigInteger;

/**
*	@author yanbit
* @date Aug 20, 2015 2:54:06 PM
*
*/
public class ExpensiveFunction implements Computable<String, BigInteger>{

  @Override
  public BigInteger compute(String arg) throws InterruptedException {
    return new BigInteger(arg);
  }
}
