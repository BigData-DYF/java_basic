package com.yanbit.thread.c5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author yanbit
 * @date Aug 20, 2015 2:55:02 PM
 *
 */
public class Memoizer3<A, V> implements Computable<A, V> {
  // private final Map<A, V> cache = new HashMap<A, V>();
  private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
  private final Computable<A, V> c;

  public Memoizer3(Computable<A, V> c) {
    super();
    this.c = c;
  }

  @Override
  public V compute(final A arg) throws InterruptedException {
    Future<V> f = cache.get(arg);
    if (f == null) {
      Callable<V> eval = new Callable<V>() {
        @Override
        public V call() throws Exception {
          return c.compute(arg);
        }
      };
      FutureTask<V> ft = new FutureTask<>(eval);
      f=ft;
      cache.put(arg, ft);
      ft.run();
    }
    try {
      return f.get();
    } catch (Exception e) {
      e.getMessage();
    }
    return null;
  }

}
