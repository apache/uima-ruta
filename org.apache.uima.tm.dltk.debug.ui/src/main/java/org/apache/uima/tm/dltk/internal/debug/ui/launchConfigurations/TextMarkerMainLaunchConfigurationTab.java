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

package org.apache.uima.tm.dltk.internal.debug.ui.launchConfigurations;

import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugPlugin;

public class TextMarkerMainLaunchConfigurationTab extends MainLaunchConfigurationTab {

  public TextMarkerMainLaunchConfigurationTab(String mode) {
    super(mode);
  }

  /*
   * @seeorg.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#
   * breakOnFirstLinePrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
   */
  @Override
  protected boolean breakOnFirstLinePrefEnabled(PreferencesLookupDelegate delegate) {
    return delegate.getBoolean(TextMarkerDebugPlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);
  }

  /*
   * @see
   * org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#dbpgLoggingPrefEnabled
   * (org.eclipse.dltk.core.PreferencesLookupDelegate)
   */
  @Override
  protected boolean dbpgLoggingPrefEnabled(PreferencesLookupDelegate delegate) {
    return delegate.getBoolean(TextMarkerDebugPlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);
  }

  /*
   * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#getNatureID()
   */
  @Override
  protected String getNatureID() {
    return TextMarkerNature.NATURE_ID;
  }

}
