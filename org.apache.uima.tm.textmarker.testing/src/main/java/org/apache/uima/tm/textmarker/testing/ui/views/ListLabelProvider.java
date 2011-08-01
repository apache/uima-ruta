package org.apache.uima.tm.textmarker.testing.ui.views;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class ListLabelProvider implements ILabelProvider {

  private final Image blue = TestViewPage.createImage("/icons/bullet_blue.png"); //$NON-NLS-1$

  private final Image green = TestViewPage.createImage("/icons/bullet_green.png"); //$NON-NLS-1$

  private final Image yellow = TestViewPage.createImage("/icons/bullet_yellow.png"); //$NON-NLS-1$

  private final Image orange = TestViewPage.createImage("/icons/bullet_orange.png"); //$NON-NLS-1$

  private final Image red = TestViewPage.createImage("/icons/bullet_red.png"); //$NON-NLS-1$

  private final Image black = TestViewPage.createImage("/icons/bullet_black.png"); //$NON-NLS-1$

  private final Image pink = TestViewPage.createImage("/icons/bullet_pink.png"); //$NON-NLS-1$

  @Override
  public Image getImage(Object element) {
    if (element instanceof TestCasData) {
      TestCasData testData = (TestCasData) element;
      if (testData.getResultPath() == null) {
        return black;
      } else {
        int tp = testData.getTruePositiveCount();
        int fp = testData.getFalsePositiveCount();
        int fn = testData.getFalseNegativeCount();
        int error = fp + fn;
        double percent = ((double) error / (double) tp);
        if (fp == 0 && fn == 0) {
          return green;
        } else if (percent >= 0.25) {
          return red;
        } else if (percent <= 0.05) {
          return yellow;
        } else if (percent < 0.25) {
          return orange;
        } else if (fp == 0) {
          return blue;
        } else if (fn == 0) {
          return pink;
        }

      }
    }
    return black;
  }

  @Override
  public String getText(Object element) {
    if (element instanceof TestCasData) {
      TestCasData testData = (TestCasData) element;
      String lastSegment = testData.getPath().lastSegment();
      if (testData.getResultPath() == null) {
        lastSegment += " [not available]";
      } else {
        int tp = testData.getTruePositiveCount();
        int fp = testData.getFalsePositiveCount();
        int fn = testData.getFalseNegativeCount();
        lastSegment += " [" + tp + "|" + fp + "|" + fn + "]";
      }
      return lastSegment;
    }
    return "error";
  }

  @Override
  public void addListener(ILabelProviderListener listener) {

  }

  @Override
  public void dispose() {
    black.dispose();
    blue.dispose();
    pink.dispose();
    green.dispose();
    yellow.dispose();
    orange.dispose();
    red.dispose();
  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {

  }

}
