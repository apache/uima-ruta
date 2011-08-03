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

package org.apache.uima.tm.textmarker.cev.statistics;

import java.util.Comparator;

public class EntryComparator implements Comparator<StatisticsEntry> {

  private int index;

  private boolean inverted;

  public EntryComparator(int index, boolean inverted) {
    super();
    this.index = index;
    this.inverted = inverted;
  }

  public int compare(StatisticsEntry e1, StatisticsEntry e2) {
    int result = 0;
    switch (index) {
      case 0:
        result = e1.getName().compareTo(e2.getName());
        break;
      case 1:
        result = ((Double) e1.getTotal()).compareTo(e2.getTotal());
        break;
      case 2:
        result = ((Integer) e1.getAmount()).compareTo(e2.getAmount());
        break;
      case 3:
        result = ((Double) e1.getPart()).compareTo(e2.getPart());
        break;
      default:
        result = 0;
    }
    if (inverted) {
      result = (int) -Math.signum(result);
    }
    return result;

  }

}
