package org.apache.uima.tm.cev.views.editor;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVEditor;
import org.apache.uima.tm.cev.extension.ICEVEditorFactory;

public class CEVAnnotationEditorFactory implements ICEVEditorFactory {

  public CEVAnnotationEditorFactory() {
  }

  public ICEVEditor createEditor(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new CEVAnnotationEditorPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return ICEVAnnotationEditorPage.class;
  }

}
