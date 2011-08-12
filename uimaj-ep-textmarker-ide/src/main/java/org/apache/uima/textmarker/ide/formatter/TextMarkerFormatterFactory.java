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

package org.apache.uima.textmarker.ide.formatter;

import java.net.URL;
import java.util.Map;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.formatter.preferences.TextMarkerFormatterModifyDialog;
import org.eclipse.dltk.formatter.AbstractScriptFormatterFactory;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialogOwner;
import org.eclipse.dltk.ui.formatter.IScriptFormatter;
import org.eclipse.dltk.ui.preferences.PreferenceKey;

public class TextMarkerFormatterFactory extends AbstractScriptFormatterFactory {

  private static final String[] KEYS = {
      // TextMarkerUI.PLUGIN_ID :
      TextMarkerFormatterConstants.FORMATTER_TAB_CHAR,
      TextMarkerFormatterConstants.FORMATTER_INDENTATION_SIZE,
      TextMarkerFormatterConstants.FORMATTER_TAB_SIZE,
      // TextMarkerFormatterPlugin.PLUGIN_ID :
      TextMarkerFormatterConstants.INDENT_BLOCK, TextMarkerFormatterConstants.INDENT_STRUCTURE,
      TextMarkerFormatterConstants.LINES_BEFORE_LONG_DECLARATIONS,
      TextMarkerFormatterConstants.MAX_LINE_LENGTH };

  public PreferenceKey[] getPreferenceKeys() {
    final PreferenceKey[] result = new PreferenceKey[KEYS.length];
    for (int i = 0; i < KEYS.length; ++i) {
      final String key = KEYS[i];
      final String qualifier;
      if (TextMarkerFormatterConstants.FORMATTER_TAB_CHAR.equals(key)
              || TextMarkerFormatterConstants.FORMATTER_INDENTATION_SIZE.equals(key)
              || TextMarkerFormatterConstants.FORMATTER_TAB_SIZE.equals(key)) {
        qualifier = TextMarkerIdePlugin.PLUGIN_ID;
      } else {
        qualifier = TextMarkerIdePlugin.PLUGIN_ID;
      }
      result[i] = new PreferenceKey(qualifier, key);
    }
    return result;
  }

  @Override
  public PreferenceKey getProfilesKey() {
    return new PreferenceKey(TextMarkerIdePlugin.PLUGIN_ID,
            TextMarkerFormatterConstants.FORMATTER_PROFILES);
  }

  public PreferenceKey getActiveProfileKey() {
    return new PreferenceKey(TextMarkerIdePlugin.PLUGIN_ID,
            TextMarkerFormatterConstants.FORMATTER_ACTIVE_PROFILE);
  }

  public IScriptFormatter createFormatter(String lineDelimiter, Map preferences) {
    return new TextMarkerFormatter(lineDelimiter, preferences);
  }

  @Override
  public URL getPreviewContent() {
    return getClass().getResource("formatPreviewScript.tm"); //$NON-NLS-1$
  }

  public IFormatterModifyDialog createDialog(IFormatterModifyDialogOwner dialogOwner) {
    return new TextMarkerFormatterModifyDialog(dialogOwner, this);
  }

}
