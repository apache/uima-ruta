package org.apache.uima.tm.textmarker.cev.explain.matched;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.cev.explain.tree.RuleMatchNode;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class MatchedTreeLabelProvider extends LabelProvider implements ILabelProvider {

  private MatchedViewPage owner;

  public MatchedTreeLabelProvider(MatchedViewPage owner) {
    super();
    this.owner = owner;
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof RuleMatchNode) {
      RuleMatchNode ruleMatchNode = (RuleMatchNode) element;
      FeatureStructure fs = ruleMatchNode.getFeatureStructure();
      if (fs != null) {
        String name = fs.getType().getName();
        return owner.getImage(name);
      }
    }
    return null;
  }

  @Override
  public String getText(Object element) {
    if (element instanceof RuleMatchNode) {
      RuleMatchNode debugNode = (RuleMatchNode) element;
      FeatureStructure fs = debugNode.getFeatureStructure();
      if (fs != null) {
        String s = ((AnnotationFS) fs).getCoveredText();
        s = s.replaceAll("[\\n\\r]", "");
        return s;
      }
    }

    return element.toString();
  }
}
