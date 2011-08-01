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
