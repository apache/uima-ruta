package org.apache.uima.tm.textmarker.testing.ui.views.fp;

import org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVFeatureTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.tm.cev.views.TextUtils;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class FalsePositiveLabelProvider extends LabelProvider implements ILabelProvider {

  private FalsePositiveViewPage owner;

  public FalsePositiveLabelProvider() {
    super();
  }

  public FalsePositiveLabelProvider(FalsePositiveViewPage owner) {
    super();
    this.owner = owner;
  }

  @Override
  public String getText(Object element) {
    if (element instanceof CEVAnnotationTreeNode) {
      CEVAnnotationTreeNode fnNode = (CEVAnnotationTreeNode) element;
      if (fnNode.getAnnotation() != null) {
        String typeName = fnNode.getAnnotation().getType().getName();
        String coveredText = fnNode.getAnnotation().getCoveredText();
        coveredText = coveredText.replaceAll("[\\n]", "").replaceAll("[\\r]", "");
        if (typeName.equals("org.apache.uima.tm.textmarker.kernel.type.FalsePositive")
                || typeName.equals("org.apache.uima.tm.textmarker.kernel.type.FalseNegative")
                || typeName.equals("org.apache.uima.tm.textmarker.kernel.type.TruePositive")) {
          return coveredText;
        }
        String name = TextUtils.shrinkNamespace(fnNode.getAnnotation().getType().getName());
        return (name + ": " + coveredText);
      }
    }
    if (element instanceof CEVTypeTreeNode) {
      CEVTypeTreeNode testNode = (CEVTypeTreeNode) element;
      return TextUtils.shrinkNamespace(testNode.getType().getName());
    }
    if (element instanceof CEVFeatureTreeNode) {
      CEVFeatureTreeNode fNode = (CEVFeatureTreeNode) element;
      return fNode.getName();
    }

    return "error";
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof CEVTypeTreeNode) {
      return owner.getCurrentCEVData().getIcon(((CEVTypeTreeNode) element).getType());
    }
    if (element instanceof CEVAnnotationTreeNode) {
      return owner.getCurrentCEVData().getIcon(
              ((CEVAnnotationTreeNode) element).getAnnotation().getType());
    }
    return null;
  }
}
