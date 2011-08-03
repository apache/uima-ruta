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

package org.apache.uima.tm.dltk.internal.ui.templates;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * Provides access to TextMarker template store.
 */
public class TextMarkerTemplateAccess extends ScriptTemplateAccess {
  // Template
  private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.textmarker.Templates";

  private static TextMarkerTemplateAccess instance;

  public static TextMarkerTemplateAccess getInstance() {
    if (instance == null) {
      instance = new TextMarkerTemplateAccess();
    }

    return instance;
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getContextTypeId()
   */
  @Override
  protected String getContextTypeId() {
    return TextMarkerUniversalTemplateContextType.CONTEXT_TYPE_ID;
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getCustomTemplatesKey()
   */
  @Override
  protected String getCustomTemplatesKey() {
    return CUSTOM_TEMPLATES_KEY;
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getPreferenceStore()
   */
  @Override
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerUI.getDefault().getPreferenceStore();
  }
}
