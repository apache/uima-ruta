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

package org.apache.uima.ruta.ide.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class RutaPreferencesMessages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.ruta.ide.ui.preferences.RutaPreferencesMessages";//$NON-NLS-1$	

  private RutaPreferencesMessages() {
    // Do not instantiate
  }

  static {
    NLS.initializeMessages(BUNDLE_NAME, RutaPreferencesMessages.class);
  }

  public static String RutaGlobalPreferencePage_description;

  public static String RutaEditorPreferencePage_general;

  public static String RutaSmartTypingConfigurationBlock_typing_smartTab;

  public static String RutaSmartTypingConfigurationBlock_closeBrackets;

  public static String RutaSmartTypingConfigurationBlock_closeStrings;

  public static String RutaSmartTypingConfigurationBlock_closeBraces;

  public static String RutaSmartTypingConfigurationBlock_typing_tabTitle;

  public static String RutaEditorPreferencePage_condition_colors;

  public static String RutaEditorPreferencePage_action_colors;

  public static String RutaEditorPreferencePage_declaration_colors;

  public static String RutaEditorPreferencePage_basicsymbols_colors;

  public static String RutaEditorPreferencePage_function_colors;

  public static String RutaEditorPreferencePage_then_colors;

  public static String RutaSmartTypingConfigurationBlock_smartPaste_full;

  public static String RutaSmartTypingConfigurationBlock_smartPaste_simple;

  public static String RutaSmartTypingConfigurationBlock_tabs_title;

  public static String RutaSmartTypingConfigurationBlock_autoclose_title;

  public static String TodoTaskDescription;

  public static String BuilderResolveImports;

  public static String BuilderImportByName;

  public static String BuilderIgnoreDuplicateShortnames;

  public static String ProjectClearOutput;

  public static String NoVMInDevMode;
}
