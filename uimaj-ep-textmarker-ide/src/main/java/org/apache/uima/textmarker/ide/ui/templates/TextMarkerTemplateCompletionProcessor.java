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

package org.apache.uima.textmarker.ide.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;

public class TextMarkerTemplateCompletionProcessor extends ScriptTemplateCompletionProcessor {

  private static char[] IGNORE = new char[] { '.' };

  public TextMarkerTemplateCompletionProcessor(ScriptContentAssistInvocationContext context) {
    super(context);
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getContextTypeId()
   */
  @Override
  protected String getContextTypeId() {
    return TextMarkerUniversalTemplateContextType.CONTEXT_TYPE_ID;
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getTemplateAccess()
   */
  @Override
  protected ScriptTemplateAccess getTemplateAccess() {
    return TextMarkerTemplateAccess.getInstance();
  }

  @Override
  protected char[] getIgnore() {
    return IGNORE;
  }
}
