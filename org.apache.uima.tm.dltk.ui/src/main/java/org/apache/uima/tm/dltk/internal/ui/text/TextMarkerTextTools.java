package org.apache.uima.tm.dltk.internal.ui.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerSemanticPositionUpdater;
import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.apache.uima.tm.dltk.ui.semantichighlighting.ISemanticHighlightingExtension;
import org.apache.uima.tm.dltk.ui.text.TextMarkerPartitions;
import org.eclipse.dltk.core.SimpleClassDLTKExtensionManager;
import org.eclipse.dltk.ui.editor.highlighting.ISemanticHighlighter;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;


public class TextMarkerTextTools extends ScriptTextTools {

  private IPartitionTokenScanner fPartitionScanner;

  private SimpleClassDLTKExtensionManager extensions = new SimpleClassDLTKExtensionManager(
          TextMarkerUI.PLUGIN_ID + ".tmSemanticHighlighting"); //$NON-NLS-1$

  private final static String[] LEGAL_CONTENT_TYPES = new String[] {
      TextMarkerPartitions.TM_STRING, TextMarkerPartitions.TM_COMMENT };

  public TextMarkerTextTools(boolean autoDisposeOnDisplayDispose) {
    super(TextMarkerPartitions.TM_PARTITIONING, LEGAL_CONTENT_TYPES, autoDisposeOnDisplayDispose);
    fPartitionScanner = new TextMarkerPartitionScanner();
  }

  @Override
  public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
          IPreferenceStore preferenceStore, ITextEditor editor, String partitioning) {
    return new TextMarkerSourceViewerConfiguration(getColorManager(), preferenceStore, editor,
            partitioning);
  }

  @Override
  public IPartitionTokenScanner getPartitionScanner() {
    return fPartitionScanner;
  }

  private ISemanticHighlightingExtension[] getExtensions() {
    Object[] objects = extensions.getObjects();
    ISemanticHighlightingExtension[] exts = new ISemanticHighlightingExtension[objects.length];

    for (int i = 0; i < objects.length; i++) {
      exts[i] = ((ISemanticHighlightingExtension) objects[i]);
    }
    return exts;
  }

  @Override
  public SemanticHighlighting[] getSemanticHighlightings() {
    List highlightings = new ArrayList();
    ISemanticHighlightingExtension[] exts = getExtensions();
    for (int i = 0; i < exts.length; i++) {
      SemanticHighlighting[] hl = exts[i].getHighlightings();
      if (hl != null) {
        highlightings.addAll(Arrays.asList(hl));
      }
    }
    SemanticHighlighting[] ret = new SemanticHighlighting[highlightings.size()];
    for (int i = 0; i < highlightings.size(); i++)
      ret[i] = (SemanticHighlighting) highlightings.get(i);

    return ret;
  }

  @Override
  public ISemanticHighlighter getSemanticPositionUpdater() {
    return new TextMarkerSemanticPositionUpdater(getExtensions());
  }

  public final static class SH extends SemanticHighlighting {

    private final String preferenceKey;

    private final String bgColor;

    private final String description;

    public SH(String editorXmlTagNameColor, String bgColor, String desc) {
      this.preferenceKey = editorXmlTagNameColor;
      this.bgColor = bgColor;
      this.description = desc;
    }

    @Override
    public boolean isSemanticOnly() {
      return description != null;
    }

    @Override
    public String getPreferenceKey() {
      return preferenceKey;
    }

    @Override
    public String getBackgroundPreferenceKey() {
      return bgColor;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((preferenceKey == null) ? 0 : preferenceKey.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      final SH other = (SH) obj;
      if (preferenceKey == null) {
        if (other.preferenceKey != null)
          return false;
      } else if (!preferenceKey.equals(other.preferenceKey))
        return false;
      return true;
    }

    @Override
    public String getDisplayName() {
      return description;
    }
  }
}
