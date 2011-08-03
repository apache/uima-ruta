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

package org.apache.uima.tm.cev.preferences;

import org.apache.uima.tm.cev.CEVPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class CEVPreferenceInitializer extends AbstractPreferenceInitializer {

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
   * initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();

    store.setDefault(CEVPreferenceConstants.P_ANNOTATION_REPR,
            CEVPreferenceConstants.P_ANNOTATION_REPR_TEXT);

    store.setDefault(CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER,
            CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER_TYPE);

    store.setDefault(CEVPreferenceConstants.P_ANNOTATION_EDITOR_TRIM, true);
    store.setDefault(CEVPreferenceConstants.P_AUTO_REFRESH, true);
    store.setDefault(CEVPreferenceConstants.P_SELECT_ONLY, false);
  }

}
