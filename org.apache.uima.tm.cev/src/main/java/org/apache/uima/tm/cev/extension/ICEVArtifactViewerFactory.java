package org.apache.uima.tm.cev.extension;

import org.apache.uima.cas.CAS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.ui.PartInitException;


public interface ICEVArtifactViewerFactory {

  ICEVArtifactViewer createArtifactViewer(CEVViewer viewer, CTabItem tabItem, CEVData casData)
          throws PartInitException;

  void setPriority(int priority);

  int getPriority();

  boolean isAble(CAS cas);

}
