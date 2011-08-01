package org.apache.uima.tm.dltk.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.jface.preference.IPreferencePage;

import org.apache.uima.tm.dltk.core.TextMarkerNature;

public class TextMarkerInterpreterComboBlock extends AbstractInterpreterComboBlock {

  @Override
  protected void showInterpreterPreferencePage() {
    IPreferencePage page = new TextMarkerInterpreterPreferencePage();
  }

  @Override
  protected String getCurrentLanguageNature() {
    return TextMarkerNature.NATURE_ID;
  }
}
