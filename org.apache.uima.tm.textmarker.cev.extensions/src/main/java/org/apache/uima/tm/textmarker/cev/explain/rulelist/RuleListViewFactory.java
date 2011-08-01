package org.apache.uima.tm.textmarker.cev.explain.rulelist;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class RuleListViewFactory implements ICEVViewFactory {

  public RuleListViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new RuleListViewPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return IRuleListViewPage.class;
  }

}
