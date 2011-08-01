package org.apache.uima.tm.cev.artifactViewer;

import org.apache.uima.cas.CAS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.AbstractArtifactViewerFactory;
import org.apache.uima.tm.cev.extension.ICEVArtifactViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;


public class TextArtifactViewerFactory extends AbstractArtifactViewerFactory {

  public ICEVArtifactViewer createArtifactViewer(CEVViewer viewer, CTabItem tabItem, CEVData casData) {
    Composite composite = new Composite(tabItem.getParent(), SWT.NONE);
    FillLayout layout = new FillLayout();
    composite.setLayout(layout);
    TextArtifactViewer text = new TextArtifactViewer(composite, SWT.READ_ONLY | SWT.WRAP
            | SWT.H_SCROLL | SWT.V_SCROLL, viewer);
    text.addMouseListener(viewer);

    tabItem.setControl(composite);
    tabItem.setText("Plain Text");

    if (casData.getDocumentText() != null) {
      text.setText(casData.getDocumentText());
    }

    return text;
  }

  public boolean isAble(CAS cas) {
    return cas.getDocumentText() != null && !"".equals(cas.getDocumentText());
  }

}
