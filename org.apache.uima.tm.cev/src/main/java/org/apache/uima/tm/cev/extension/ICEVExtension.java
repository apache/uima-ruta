package org.apache.uima.tm.cev.extension;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.data.ICEVAnnotationListener;

public interface ICEVExtension extends ICEVAnnotationListener {

  void viewChanged(int newIndex);

  void casChanged(CEVDocument casDocument);
}
