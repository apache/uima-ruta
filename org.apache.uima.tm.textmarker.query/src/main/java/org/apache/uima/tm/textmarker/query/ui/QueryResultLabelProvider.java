package org.apache.uima.tm.textmarker.query.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class QueryResultLabelProvider implements ILabelProvider {
  @Override
  public Image getImage(Object element) {
    return null;
  }

  @Override
  public String getText(Object element) {
    if (element instanceof QueryResult) {
      QueryResult qr = (QueryResult) element;
      String text = qr.getText();
      // if (text.length() > 100) {
      // text = text.substring(0, 100) + "...";
      // }
      text += " (in " + qr.getFile().getName() + ")";
      return text;
    }
    return "error";
  }

  @Override
  public void addListener(ILabelProviderListener listener) {

  }

  @Override
  public void dispose() {
  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {

  }
}
