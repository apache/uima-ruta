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

package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class TextMarkerPreferencesMessages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.tm.dltk.internal.ui.preferences.TextMarkerPreferencesMessages";//$NON-NLS-1$	

  private TextMarkerPreferencesMessages() {
    // Do not instantiate
  }

  static {
    NLS.initializeMessages(BUNDLE_NAME, TextMarkerPreferencesMessages.class);
  }

  public static String TextMarkerGlobalPreferencePage_description;

  public static String TextMarkerEditorPreferencePage_general;

  public static String TextMarkerSmartTypingConfigurationBlock_typing_smartTab;

  public static String TextMarkerSmartTypingConfigurationBlock_closeBrackets;

  public static String TextMarkerSmartTypingConfigurationBlock_closeStrings;

  public static String TextMarkerSmartTypingConfigurationBlock_closeBraces;

  public static String TextMarkerSmartTypingConfigurationBlock_typing_tabTitle;

  public static String TextMarkerEditorPreferencePage_condition_colors;

  public static String TextMarkerEditorPreferencePage_action_colors;

  public static String TextMarkerEditorPreferencePage_declaration_colors;

  public static String TextMarkerEditorPreferencePage_basicsymbols_colors;

  public static String TextMarkerEditorPreferencePage_function_colors;

  public static String TextMarkerEditorPreferencePage_then_colors;

  public static String TextMarkerSmartTypingConfigurationBlock_smartPaste_full;

  public static String TextMarkerSmartTypingConfigurationBlock_smartPaste_simple;

  public static String TextMarkerSmartTypingConfigurationBlock_tabs_title;

  public static String TextMarkerSmartTypingConfigurationBlock_autoclose_title;

  public static String TodoTaskDescription;

  public static String BuilderResolveImports;

  public static String BuilderImportByName;

  public static String BuilderIgnoreDuplicateShortnames;
}
