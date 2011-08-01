package org.apache.uima.tm.textmarker.query.ui;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class QueryResultContentProvider implements IStructuredContentProvider {

  @Override
  public void dispose() {

  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

  }

  @Override
  public Object[] getElements(Object inputElement) {
    return ((ArrayList<?>) inputElement).toArray();
  }
}
