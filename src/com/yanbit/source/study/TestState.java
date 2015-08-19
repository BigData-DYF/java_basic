package com.yanbit.source.study;

/**
 * @author yanbit
 * @date Aug 19, 2015 10:38:07 AM
 *
 */
public class TestState {

  // /** Constructed but not initialized */
  // NOTINITED(0, "NOTINITED"),
  //
  // /** Initialized but not started or stopped */
  // INITED(1, "INITED"),
  //
  // /** started and not stopped */
  // STARTED(2, "STARTED"),
  //
  // /** stopped. No further state transitions are permitted */
  // STOPPED(3, "STOPPED");
  
  private static final boolean[][] statemap = {
      // uninited inited started stopped
      /* uninited */ { false, true, false, true },
      /* inited */ { false, true, true, true },
      /* started */ { false, false, true, true },
      /* stopped */ { false, false, false, true }, };
  private static int intArray[ ][ ]={{1,2},{2,3},{3,4,5}}; 
  public static void main(String[] args) {
    System.out.println(statemap[0][1]);
  }
}
