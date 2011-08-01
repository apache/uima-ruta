package org.apache.uima.tm.dltk.internal.ui.infoviews;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.infoviews.AbstractDocumentationView;
import org.eclipse.jface.preference.IPreferenceStore;


public class TextMarkerDocumentationView extends AbstractDocumentationView {
  @Override
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerUI.getDefault().getPreferenceStore();
  }

  @Override
  protected String getNature() {
    return TextMarkerNature.NATURE_ID;
  }
}
