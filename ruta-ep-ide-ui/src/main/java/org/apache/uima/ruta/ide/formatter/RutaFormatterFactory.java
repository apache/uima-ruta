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

package org.apache.uima.ruta.ide.formatter;

import java.net.URL;
import java.util.Map;

import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.formatter.preferences.RutaFormatterModifyDialog;
import org.eclipse.dltk.formatter.AbstractScriptFormatterFactory;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialogOwner;
import org.eclipse.dltk.ui.formatter.IScriptFormatter;
import org.eclipse.dltk.ui.preferences.PreferenceKey;

public class RutaFormatterFactory extends AbstractScriptFormatterFactory {

  private static final String[] KEYS = {
      // RutaUI.PLUGIN_ID :
      RutaFormatterConstants.FORMATTER_TAB_CHAR, RutaFormatterConstants.FORMATTER_INDENTATION_SIZE,
      RutaFormatterConstants.FORMATTER_TAB_SIZE,
      // RutaFormatterPlugin.PLUGIN_ID :
      RutaFormatterConstants.INDENT_BLOCK, RutaFormatterConstants.INDENT_STRUCTURE,
      RutaFormatterConstants.LINES_BEFORE_LONG_DECLARATIONS, RutaFormatterConstants.MAX_LINE_LENGTH };

  public PreferenceKey[] getPreferenceKeys() {
    final PreferenceKey[] result = new PreferenceKey[KEYS.length];
    for (int i = 0; i < KEYS.length; ++i) {
      final String key = KEYS[i];
      final String qualifier;
      if (RutaFormatterConstants.FORMATTER_TAB_CHAR.equals(key)
              || RutaFormatterConstants.FORMATTER_INDENTATION_SIZE.equals(key)
              || RutaFormatterConstants.FORMATTER_TAB_SIZE.equals(key)) {
        qualifier = RutaIdeUIPlugin.PLUGIN_ID;
      } else {
        qualifier = RutaIdeUIPlugin.PLUGIN_ID;
      }
      result[i] = new PreferenceKey(qualifier, key);
    }
    return result;
  }

  @Override
  public PreferenceKey getProfilesKey() {
    return new PreferenceKey(RutaIdeUIPlugin.PLUGIN_ID, RutaFormatterConstants.FORMATTER_PROFILES);
  }

  public PreferenceKey getActiveProfileKey() {
    return new PreferenceKey(RutaIdeUIPlugin.PLUGIN_ID,
            RutaFormatterConstants.FORMATTER_ACTIVE_PROFILE);
  }

  public IScriptFormatter createFormatter(String lineDelimiter, Map preferences) {
    return new RutaFormatter(lineDelimiter, preferences);
  }

  @Override
  public URL getPreviewContent() {
    return getClass().getResource("formatPreviewScript" + RutaEngine.SCRIPT_FILE_EXTENSION); //$NON-NLS-1$
  }

  public IFormatterModifyDialog createDialog(IFormatterModifyDialogOwner dialogOwner) {
    return new RutaFormatterModifyDialog(dialogOwner, this);
  }

}
