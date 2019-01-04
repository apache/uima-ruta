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

package org.apache.uima.ruta.ide.ui.handlers;

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.debug.RutaDebugConstants;
import org.eclipse.dltk.debug.ui.handlers.AbstractToggleGlobalVariableHandler;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.jface.preference.IPreferenceStore;

public class ToggleGlobalVariablesHandler extends AbstractToggleGlobalVariableHandler {
  /*
   * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getModelId()
   */
  @Override
  protected String getModelId() {
    return RutaDebugConstants.DEBUG_MODEL_ID;
  }

  /*
   * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getPreferenceStore()
   */
  @Override
  protected IPreferenceStore getPreferenceStore() {
    return new PreferencesAdapter(RutaIdeUIPlugin.getDefault().getPluginPreferences());
  }
}
