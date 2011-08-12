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

package org.apache.uima.textmarker.ide.debug.ui.preferences;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.preferences.AbstractDebuggingOptionsBlock;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class TextMarkerDebugPreferencePage extends
        AbstractConfigurationBlockPropertyAndPreferencePage {

  private static PreferenceKey BREAK_ON_FIRST_LINE = new PreferenceKey(
          TextMarkerIdePlugin.PLUGIN_ID, DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);

  private static PreferenceKey ENABLE_DBGP_LOGGING = new PreferenceKey(
          TextMarkerIdePlugin.PLUGIN_ID, DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);

  private static String PREFERENCE_PAGE_ID = "org.apache.uima.textmarker.ide.preferences.debug";

  private static String PROPERTY_PAGE_ID = "org.apache.uima.textmarker.ide.propertyPage.debug";

  @Override
  protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
          IProject project, IWorkbenchPreferenceContainer container) {
    return new AbstractDebuggingOptionsBlock(newStatusChangedListener, project, getKeys(),
            container) {

      @Override
      protected PreferenceKey getBreakOnFirstLineKey() {
        return BREAK_ON_FIRST_LINE;
      }

      @Override
      protected PreferenceKey getDbgpLoggingEnabledKey() {
        return ENABLE_DBGP_LOGGING;
      }
    };
  }

  /*
   * @see
   * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
   */
  @Override
  protected String getHelpId() {
    return null;
  }

  /*
   * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPreferencePageId()
   */
  @Override
  protected String getPreferencePageId() {
    return PREFERENCE_PAGE_ID;
  }

  /*
   * @seeorg.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#
   * getProjectHelpId()
   */
  @Override
  protected String getProjectHelpId() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPropertyPageId()
   */
  @Override
  protected String getPropertyPageId() {
    return PROPERTY_PAGE_ID;
  }

  /*
   * @see
   * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setDescription
   * ()
   */
  @Override
  protected void setDescription() {
    setDescription(TextMarkerDebugPreferenceMessages.TextMarkerDebugPreferencePage_description);
  }

  /*
   * @seeorg.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#
   * setPreferenceStore()
   */
  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(new PreferencesAdapter(TextMarkerIdePlugin.getDefault()
            .getPluginPreferences()));
  }

  private PreferenceKey[] getKeys() {
    return new PreferenceKey[] { BREAK_ON_FIRST_LINE, ENABLE_DBGP_LOGGING };
  }
}
