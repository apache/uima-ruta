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

package org.apache.uima.ruta.ide.ui.text.completion;

import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;

public class RutaScriptCompletionProposal extends ScriptCompletionProposal {

  public RutaScriptCompletionProposal(String replacementString, int replacementOffset,
          int replacementLength, Image image, String displayString, int relevance) {
    super(replacementString, replacementOffset, replacementLength, image, displayString, relevance);
  }

  public RutaScriptCompletionProposal(String replacementString, int replacementOffset,
          int replacementLength, Image image, String displayString, int relevance, boolean isInDoc) {
    super(replacementString, replacementOffset, replacementLength, image, displayString, relevance,
            isInDoc);
  }

  @Override
  protected boolean isSmartTrigger(char trigger) {
    if (trigger == '.') {
      return true;
    }
    return false;
  }

  @Override
  protected boolean isCamelCaseMatching() {
    return true;
  }

  @Override
  protected boolean insertCompletion() {
    IPreferenceStore preference = RutaIdePlugin.getDefault().getPreferenceStore();
    return preference.getBoolean(PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
  }

  @Override
  protected boolean isValidPrefix(String prefix) {
    String[] split = getDisplayString().split("[.]");
    return isPrefix(prefix, getDisplayString()) || isPrefix(prefix, split[split.length - 1]);
  }

}
