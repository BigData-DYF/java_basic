package com.yanbit.source.study;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShutdownHookManager{
  public static void main(String[] args) {
    Thread t1 = new Thread(){
      @Override
      public void run() {
        System.out.println("hello 1");
      }
    };
    
    Thread t2 = new Thread(){
      @Override
      public void run() {
        System.out.println("hello 2");
      }
    };
    
    HookManager.get().addShutdownHook(t1, 1);
    HookManager.get().addShutdownHook(t2, 2);
    
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class HookManager {

  private static final HookManager MGR = new HookManager();
  static {
    Runtime.getRuntime().addShutdownHook(
      new Thread() {
        @Override
        public void run() {
          MGR.shutdownInProgress.set(true);
          for (Runnable hook: MGR.getShutdownHooksInOrder()) {
            try {
              hook.run();
            } catch (Throwable ex) {
            }
          }
        }
      }
    );
  }

  /**
   * Return <code>ShutdownHookManager</code> singleton.
   *
   * @return <code>ShutdownHookManager</code> singleton.
   */
  public static HookManager get() {
    return MGR;
  }

  /**
   * Private structure to store ShutdownHook and its priority.
   */
  private static class HookEntry {
    Runnable hook;
    int priority;

    public HookEntry(Runnable hook, int priority) {
      this.hook = hook;
      this.priority = priority;
    }

    @Override
    public int hashCode() {
      return hook.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      boolean eq = false;
      if (obj != null) {
        if (obj instanceof HookEntry) {
          eq = (hook == ((HookEntry)obj).hook);
        }
      }
      return eq;
    }

  }

  private Set<HookEntry> hooks =
    Collections.synchronizedSet(new HashSet<HookEntry>());

  private AtomicBoolean shutdownInProgress = new AtomicBoolean(false);

  //private to constructor to ensure singularity
  private HookManager() {
  }

  /**
   * Returns the list of shutdownHooks in order of execution,
   * Highest priority first.
   *
   * @return the list of shutdownHooks in order of execution.
   */
  List<Runnable> getShutdownHooksInOrder() {
    List<HookEntry> list;
    synchronized (MGR.hooks) {
      list = new ArrayList<HookEntry>(MGR.hooks);
    }
    Collections.sort(list, new Comparator<HookEntry>() {

      //reversing comparison so highest priority hooks are first
      @Override
      public int compare(HookEntry o1, HookEntry o2) {
        return o2.priority - o1.priority;
      }
    });
    List<Runnable> ordered = new ArrayList<Runnable>();
    for (HookEntry entry: list) {
      ordered.add(entry.hook);
    }
    return ordered;
  }

  /**
   * Adds a shutdownHook with a priority, the higher the priority
   * the earlier will run. ShutdownHooks with same priority run
   * in a non-deterministic order.
   *
   * @param shutdownHook shutdownHook <code>Runnable</code>
   * @param priority priority of the shutdownHook.
   */
  public void addShutdownHook(Runnable shutdownHook, int priority) {
    if (shutdownHook == null) {
      throw new IllegalArgumentException("shutdownHook cannot be NULL");
    }
    if (shutdownInProgress.get()) {
      throw new IllegalStateException("Shutdown in progress, cannot add a shutdownHook");
    }
    hooks.add(new HookEntry(shutdownHook, priority));
  }

  /**
   * Removes a shutdownHook.
   *
   * @param shutdownHook shutdownHook to remove.
   * @return TRUE if the shutdownHook was registered and removed,
   * FALSE otherwise.
   */
  public boolean removeShutdownHook(Runnable shutdownHook) {
    if (shutdownInProgress.get()) {
      throw new IllegalStateException("Shutdown in progress, cannot remove a shutdownHook");
    }
    return hooks.remove(new HookEntry(shutdownHook, 0));
  }

  /**
   * Indicates if a shutdownHook is registered or not.
   *
   * @param shutdownHook shutdownHook to check if registered.
   * @return TRUE/FALSE depending if the shutdownHook is is registered.
   */
  public boolean hasShutdownHook(Runnable shutdownHook) {
    return hooks.contains(new HookEntry(shutdownHook, 0));
  }
  
  /**
   * Indicates if shutdown is in progress or not.
   * 
   * @return TRUE if the shutdown is in progress, otherwise FALSE.
   */
  public boolean isShutdownInProgress() {
    return shutdownInProgress.get();
  }

}