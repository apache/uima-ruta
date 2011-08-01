package org.apache.uima.tm.textmarker.testing.ui.views.evalDataTable;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class EvalTableLabelProvider implements ITableLabelProvider {

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    TypeEvalData entry = (TypeEvalData) element;
    
    switch(columnIndex) {
    
      case 0:
        return entry.getTypeName();
      case 1:
        return String.valueOf(entry.getTruePositives());
      case 2:
        return String.valueOf(entry.getFalsePositives());
      case 3:
        return String.valueOf(entry.getFalseNegatives());
      case 4:
        return String.valueOf(entry.getPrecision());
      case 5:
        return String.valueOf(entry.getRecall());
      case 6:
        return String.valueOf(entry.getFOne());
    }
     
    
    
    return null;
  }

  @Override
  public void addListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

}
