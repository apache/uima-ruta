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

package org.apache.uima.ruta.ide.formatter;

import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class RutaFormatterPreferenceInitializer extends AbstractPreferenceInitializer {

  public void initializeDefaultPreferences() {
    RutaIdePlugin plugin = RutaIdePlugin.getDefault();
    IPreferenceStore store = plugin.getPreferenceStore();

    store.setDefault(RutaFormatterConstants.INDENT_BLOCK, true);
    store.setDefault(RutaFormatterConstants.INDENT_STRUCTURE, true);

    store.setDefault(RutaFormatterConstants.LINES_BEFORE_LONG_DECLARATIONS, 1);

    store.setDefault(RutaFormatterConstants.LINES_PRESERVE, 1);

    store.setDefault(RutaFormatterConstants.MAX_LINE_LENGTH, 100);
  }
}
