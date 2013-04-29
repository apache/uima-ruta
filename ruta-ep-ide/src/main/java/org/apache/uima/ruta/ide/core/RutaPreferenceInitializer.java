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

package org.apache.uima.ruta.ide.core;

import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class RutaPreferenceInitializer extends AbstractPreferenceInitializer {

  public RutaPreferenceInitializer() {
  }

  public void initializeDefaultPreferences() {
    IPreferenceStore store = RutaIdePlugin.getDefault().getPreferenceStore();
//    TaskTagUtils.initializeDefaultValues(store);
    store.setDefault(RutaCorePreferences.BUILDER_IMPORT_BY_NAME, false);
    store.setDefault(RutaCorePreferences.BUILDER_RESOLVE_IMPORTS, false);
    store.setDefault(RutaCorePreferences.BUILDER_IGNORE_DUPLICATE_SHORTNAMES, false);
    store.setDefault(RutaCorePreferences.PROJECT_CLEAR_OUTPUT, false);
  }

}
