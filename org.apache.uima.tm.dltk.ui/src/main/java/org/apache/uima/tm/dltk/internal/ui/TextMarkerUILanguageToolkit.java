package org.apache.uima.tm.dltk.internal.ui;

import org.apache.uima.tm.dltk.core.TextMarkerConstants;
import org.apache.uima.tm.dltk.core.TextMarkerLanguageToolkit;
import org.apache.uima.tm.dltk.internal.core.parser.TextMarkerParseUtils;
import org.apache.uima.tm.dltk.internal.ui.editor.TextMarkerEditor;
import org.apache.uima.tm.dltk.internal.ui.text.SimpleTextMarkerSourceViewerConfiguration;
import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;


public class TextMarkerUILanguageToolkit extends AbstractDLTKUILanguageToolkit {

  private static TextMarkerUILanguageToolkit toolkit = null;

  public static IDLTKUILanguageToolkit getInstance() {
    if (toolkit == null) {
      toolkit = new TextMarkerUILanguageToolkit();
    }
    return toolkit;
  }

  private static ScriptElementLabels sInstance = new ScriptElementLabels() {
    @Override
    protected void getScriptFolderLabel(IScriptFolder folder, StringBuffer buf) {
      String name = folder.getElementName();
      name = name.replace(IScriptFolder.PACKAGE_DELIMITER, '.');
      buf.append(name);
    }

    @Override
    protected void getFieldLabel(IField field, long flags, StringBuffer buf) {
      super.getFieldLabel(field, flags, buf);
      int i = TextMarkerParseUtils.getTypeOfIModelElement(field);
      String type = TMTypeConstants.typeStringOfInt.get(i);
      if (type != null) {
        type = type.toLowerCase();
        buf.append(" : ");
        buf.append(type);
      }
    }
  };

  @Override
  public ScriptElementLabels getScriptElementLabels() {
    return sInstance;
  }

  public IPreferenceStore getPreferenceStore() {
    return TextMarkerUI.getDefault().getPreferenceStore();
  }

  public IDLTKLanguageToolkit getCoreToolkit() {
    return TextMarkerLanguageToolkit.getDefault();
  }

  public IDialogSettings getDialogSettings() {
    return TextMarkerUI.getDefault().getDialogSettings();
  }

  @Override
  public String getPartitioningId() {
    return TextMarkerConstants.TM_PARTITIONING;
  }

  @Override
  public String getEditorId(Object inputElement) {
    return TextMarkerEditor.EDITOR_ID;
  }

  @Override
  public String getInterpreterContainerId() {
    return "org.apache.uima.tm.dltk.launching.INTERPRETER_CONTAINER";
  }

  @Override
  public ScriptUILabelProvider createScriptUILabelProvider() {
    return null;
  }

  @Override
  public boolean getProvideMembers(ISourceModule element) {
    return true;
  }

  @Override
  public ScriptTextTools getTextTools() {
    return TextMarkerUI.getDefault().getTextTools();
  }

  @Override
  public ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
    return new SimpleTextMarkerSourceViewerConfiguration(getTextTools().getColorManager(),
            getPreferenceStore(), null, getPartitioningId(), false);
  }

  private static final String INTERPRETERS_PREFERENCE_PAGE_ID = "org.apache.uima.tm.dltk.preferences.interpreters";

  private static final String DEBUG_PREFERENCE_PAGE_ID = "org.apache.uima.tm.dltk.preferences.debug";

  @Override
  public String getInterpreterPreferencePage() {
    return INTERPRETERS_PREFERENCE_PAGE_ID;
  }

  @Override
  public String getDebugPreferencePage() {
    return DEBUG_PREFERENCE_PAGE_ID;
  }

  private static final String[] EDITOR_PREFERENCE_PAGES_IDS = {
      "org.apache.uima.tm.dltk.preferences.editor",
      "org.apache.uima.tm.dltk.ui.editor.SyntaxColoring",
      "org.apache.uima.tm.dltk.ui.editor.SmartTyping",
      "org.apache.uima.tm.dltk.ui.editor.TextMarkerFolding" };

  @Override
  public String[] getEditorPreferencePages() {
    return EDITOR_PREFERENCE_PAGES_IDS;
  }
}