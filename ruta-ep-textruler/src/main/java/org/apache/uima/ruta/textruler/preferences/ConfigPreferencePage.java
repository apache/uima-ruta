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

import org.apache.uima.ruta.textruler.TextRulerPlugin;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ConfigPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public static String ID = "org.apache.uima.ruta.textruler.config";
  private BooleanFieldEditor removeBasics;
  private BooleanFieldEditor lowMemoryProfile;
  private IntegerFieldEditor maxErrorRate;
  private IntegerFieldEditor casCache;


  public ConfigPreferencePage() {
    setPreferenceStore(TextRulerPlugin.getDefault().getPreferenceStore());
    setDescription("General settings for the different TextRuler methods.");
  }

  public void init(IWorkbench workbench) {

  }

  @Override
  protected void createFieldEditors() {
    removeBasics = new BooleanFieldEditor(TextRulerPreferences.REMOVE_BASICS,
            "Remove basic annotations after testing rules.", getFieldEditorParent());
    addField(removeBasics);

    lowMemoryProfile = new BooleanFieldEditor(TextRulerPreferences.LOW_MEMORY_PROFILE,
            "Use low memory profile.", getFieldEditorParent());
    addField(lowMemoryProfile);
    
    maxErrorRate = new IntegerFieldEditor(TextRulerPreferences.MAX_ERROR_RATE,
            "Maximum error rate during testing.", getFieldEditorParent());
    addField(maxErrorRate);
    
    casCache = new IntegerFieldEditor(TextRulerPreferences.CAS_CACHE,
            "CAS cache size.", getFieldEditorParent());
    addField(casCache);
    
  }
}
