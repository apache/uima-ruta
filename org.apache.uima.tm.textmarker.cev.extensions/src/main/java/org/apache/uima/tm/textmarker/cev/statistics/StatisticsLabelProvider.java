package org.apache.uima.tm.textmarker.cev.statistics;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class StatisticsLabelProvider extends LabelProvider implements ITableLabelProvider {

  private StatisticsViewPage owner;

  public StatisticsLabelProvider(StatisticsViewPage owner) {
    super();
    this.owner = owner;
  }

  public Image getColumnImage(Object element, int columnIndex) {
    return null;
  }

  public String getColumnText(Object element, int columnIndex) {
    StatisticsEntry entry = (StatisticsEntry) element;
    switch (columnIndex) {
      case 0:
        return entry.getName();
      case 1:
        return entry.getTotal() + "ms";
      case 2:
        return entry.getAmount() + "";
      case 3:
        return entry.getPart() + "ms";
      default:
        throw new RuntimeException("Should not happen");
    }

  }

}
