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
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.apache.uima.textmarker.ide.debug.TextMarkerDebugConstants;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.ui.preferences.AbstractDebuggingEngineOptionsBlock;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class TextMarkerDebuggingEnginePreferencePage extends
        AbstractConfigurationBlockPropertyAndPreferencePage {

  private static PreferenceKey DEBUGGING_ENGINE = new PreferenceKey(TextMarkerIdePlugin.PLUGIN_ID,
          TextMarkerDebugConstants.DEBUGGING_ENGINE_ID_KEY);

  private static final String PREFERENCE_PAGE_ID = "org.apache.uima.textmarker.ide.preferences.debug.engines";

  private static final String PROPERTY_PAGE_ID = "org.apache.uima.textmarker.ide.propertyPage.debug.engines";

  @Override
  protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
          IProject project, IWorkbenchPreferenceContainer container) {
    return new AbstractDebuggingEngineOptionsBlock(newStatusChangedListener, project, getKeys(),
            container) {

      @Override
      protected String getNatureId() {
        return TextMarkerNature.NATURE_ID;
      }

      @Override
      protected PreferenceKey getSavedContributionKey() {
        return DEBUGGING_ENGINE;
      }
    };
  }

  /*
   * @see
   * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
   */
  @Override
  protected String getHelpId() {
    // TODO Auto-generated method stub
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
    setDescription(TextMarkerDebugPreferenceMessages.TextMarkerDebugEnginePreferencePage_description);
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
    return new PreferenceKey[] { DEBUGGING_ENGINE };
  }
}
