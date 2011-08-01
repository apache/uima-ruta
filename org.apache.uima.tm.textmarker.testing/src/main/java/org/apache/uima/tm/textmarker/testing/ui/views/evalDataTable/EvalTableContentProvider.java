package org.apache.uima.tm.textmarker.testing.ui.views.evalDataTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class EvalTableContentProvider implements IStructuredContentProvider {

  @Override
  public Object[] getElements(Object inputElement) {
    
    if  (inputElement instanceof HashMap) {
      return ((HashMap)inputElement).values().toArray();
    }
    return null;
  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub

  }

}
