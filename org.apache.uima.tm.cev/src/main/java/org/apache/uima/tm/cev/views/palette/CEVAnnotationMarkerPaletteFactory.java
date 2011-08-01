package org.apache.uima.tm.cev.views.palette;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVEditor;
import org.apache.uima.tm.cev.extension.ICEVEditorFactory;

public class CEVAnnotationMarkerPaletteFactory implements ICEVEditorFactory {

  public CEVAnnotationMarkerPaletteFactory() {
  }

  public ICEVEditor createEditor(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new CEVAnnotationMarkerPalettePage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return ICEVAnnotationMarkerPalettePage.class;
  }

}
