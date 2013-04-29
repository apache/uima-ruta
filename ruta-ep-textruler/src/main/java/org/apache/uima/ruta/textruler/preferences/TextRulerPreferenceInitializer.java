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

package org.apache.uima.ruta.textruler.preferences;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.ruta.textruler.TextRulerPlugin;
import org.apache.uima.ruta.textruler.extension.TextRulerController;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerController;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class TextRulerPreferenceInitializer extends AbstractPreferenceInitializer {

  public TextRulerPreferenceInitializer() {
  }

  public void initializeDefaultPreferences() {
    IPreferenceStore store = TextRulerPlugin.getDefault().getPreferenceStore();
    
    List<TextRulerLearnerController> availableControllers = TextRulerController.getAvailableControllers();
    for (TextRulerLearnerController each : availableControllers) {
      String id = each.getID();
      Map<String, Object> defaultValues = each.getFactory().getAlgorithmParameterStandardValues();
      Set<Entry<String,Object>> entrySet = defaultValues.entrySet();
      for (Entry<String, Object> entry : entrySet) {
        String key = id + "." + entry.getKey();
        Object value = entry.getValue();
        if(value instanceof Integer) {
          store.setDefault(key, (Integer) value);
        } else if(value instanceof Double) {
          store.setDefault(key, (Double) value);
        } else if(value instanceof Float) {
          store.setDefault(key, (Float) value);
        } else if(value instanceof Boolean) {
          store.setDefault(key, (Boolean) value);
        } else if(value instanceof String) {
          store.setDefault(key, (String) value);
        }
      }
    }
  }
}
