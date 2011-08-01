package org.apache.uima.tm.textruler.tools;

import org.apache.uima.tm.textruler.core.TextRulerToolkit;

public class MemoryWatch {

  private static long lastFreeMemory = 0;

  private static long lastTotalMemory = 0;

  private static long count = 0;

  private static long lastFreeMemAfterGuessedGC = 0;

  public static synchronized void watch() {
    long free = Runtime.getRuntime().freeMemory();
    long total = Runtime.getRuntime().totalMemory();
    long max = Runtime.getRuntime().maxMemory();
    long used = total - free;

    boolean show = total != lastTotalMemory || (count % 5000 == 0);

    if (total == max) {
      if (free > lastFreeMemory && free != lastFreeMemAfterGuessedGC) // gc
      // seemd
      // to
      // clean
      // up
      // the
      // memory
      {

        lastFreeMemAfterGuessedGC = free;
        show = true;
      }
    }

    if (show) {
      TextRulerToolkit.log(count + "\tfree: " + free + "\ttotal: " + total + "\tused: " + used
              + "\tmax: " + max + "\tfreeAfterGC: " + lastFreeMemAfterGuessedGC);
    }
    lastFreeMemory = free;
    lastTotalMemory = total;
    count++;
  }

}
