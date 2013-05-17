/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.ide.ui.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.apache.uima.ruta.ide.ui.ISemanticHighlightingExtension;
import org.apache.uima.ruta.ide.ui.RutaPartitions;
import org.apache.uima.ruta.ide.ui.RutaSemanticPositionUpdater;
import org.eclipse.dltk.core.SimpleClassDLTKExtensionManager;
import org.eclipse.dltk.ui.editor.highlighting.ISemanticHighlightingUpdater;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;

public class RutaTextTools extends ScriptTextTools {

  private IPartitionTokenScanner fPartitionScanner;

  private SimpleClassDLTKExtensionManager extensions = new SimpleClassDLTKExtensionManager(
          RutaIdePlugin.PLUGIN_ID + ".rutaSemanticHighlighting"); //$NON-NLS-1$

  private final static String[] LEGAL_CONTENT_TYPES = new String[] { RutaPartitions.RUTA_STRING,
      RutaPartitions.RUTA_COMMENT };

  public RutaTextTools(boolean autoDisposeOnDisplayDispose) {
    super(RutaPartitions.RUTA_PARTITIONING, LEGAL_CONTENT_TYPES, autoDisposeOnDisplayDispose);
    fPartitionScanner = new RutaPartitionScanner();
  }

  @Override
  public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
          IPreferenceStore preferenceStore, ITextEditor editor, String partitioning) {
    return new RutaSourceViewerConfiguration(getColorManager(), preferenceStore, editor,
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

  public ISemanticHighlightingUpdater getSemanticPositionUpdater(String natureId) {
    return new RutaSemanticPositionUpdater(getExtensions());
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
