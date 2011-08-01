package org.apache.uima.tm.textmarker.cev.statistics;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class StatisticsContentProvider implements IStructuredContentProvider {

  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

  public Object[] getElements(Object inputElement) {
    @SuppressWarnings("unchecked")
    List<StatisticsEntry> entries = (List<StatisticsEntry>) inputElement;
    return entries.toArray();

  }

}
