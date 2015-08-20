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
public class Memoizer<A, V> implements Computable<A, V> {
  // private final Map<A, V> cache = new HashMap<A, V>();
  private final ConcurrentHashMap<A, Future<V>> cache =
      new ConcurrentHashMap<A, Future<V>>();
  private final Computable<A, V> c;

  public Memoizer(Computable<A, V> c) {
    super();
    this.c = c;
  }

  @Override
  public V compute(final A arg) throws InterruptedException {
    while (true) {
      Future<V> f = cache.get(arg);
      if (f == null) {
        Callable<V> eval = new Callable<V>() {
          @Override
          public V call() throws Exception {
            return c.compute(arg);
          }
        };
        FutureTask<V> ft = new FutureTask<>(eval);
        f = cache.putIfAbsent(arg, ft);
        if (f == null) {
          f = ft;
          ft.run();
        }
      }
      try {
        return f.get();
      } catch (Exception e) {
      }
    }
  }
}
