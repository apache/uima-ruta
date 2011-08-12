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

package org.apache.uima.textmarker.ide.ui.preferences;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.AbstractTodoTaskOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class TextMarkerTodoTaskPreferencePage extends
        AbstractConfigurationBlockPropertyAndPreferencePage {

  static final PreferenceKey CASE_SENSITIVE = AbstractTodoTaskOptionsBlock
          .createCaseSensitiveKey(TextMarkerIdePlugin.PLUGIN_ID);

  static final PreferenceKey ENABLED = AbstractTodoTaskOptionsBlock
          .createEnabledKey(TextMarkerIdePlugin.PLUGIN_ID);

  static final PreferenceKey TAGS = AbstractTodoTaskOptionsBlock
          .createTagKey(TextMarkerIdePlugin.PLUGIN_ID);

  protected PreferenceKey[] getPreferenceKeys() {
    return new PreferenceKey[] { TAGS, ENABLED, CASE_SENSITIVE };
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage #getHelpId()
   */
  @Override
  protected String getHelpId() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage #setDescription()
   */
  @Override
  protected void setDescription() {
    setDescription(TextMarkerPreferencesMessages.TodoTaskDescription);
  }

  protected Preferences getPluginPreferences() {
    return TextMarkerIdePlugin.getDefault().getPluginPreferences();
  }

  @Override
  protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
          IProject project, IWorkbenchPreferenceContainer container) {
    return new AbstractTodoTaskOptionsBlock(newStatusChangedListener, project, getPreferenceKeys(),
            container) {
      @Override
      protected PreferenceKey getTags() {
        return TAGS;
      }

      @Override
      protected PreferenceKey getEnabledKey() {
        return ENABLED;
      }

      @Override
      protected PreferenceKey getCaseSensitiveKey() {
        return CASE_SENSITIVE;
      }
    };
  }

  @Override
  protected String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }

  @Override
  protected String getProjectHelpId() {
    return null;
  }

  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(new PreferencesAdapter(TextMarkerIdePlugin.getDefault()
            .getPluginPreferences()));
  }

  @Override
  protected String getPreferencePageId() {
    return "org.apache.uima.textmarker.ide.preferences.todo";
  }

  @Override
  protected String getPropertyPageId() {
    return "org.apache.uima.textmarker.ide.propertyPage.todo";
  }

}
