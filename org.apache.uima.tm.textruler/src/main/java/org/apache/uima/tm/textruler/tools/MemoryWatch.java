/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/

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
