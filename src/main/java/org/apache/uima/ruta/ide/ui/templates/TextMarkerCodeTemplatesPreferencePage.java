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

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.ui.TextMarkerPartitions;
import org.apache.uima.textmarker.ide.ui.text.SimpleTextMarkerSourceViewerConfiguration;
import org.apache.uima.textmarker.ide.ui.text.TextMarkerTextTools;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.text.IDocument;

/**
 * TextMarker code templates preference page
 */
public class TextMarkerCodeTemplatesPreferencePage extends ScriptTemplatePreferencePage {

  /*
   * @see
   * org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#createSourceViewerConfiguration()
   */
  @Override
  protected ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
    return new SimpleTextMarkerSourceViewerConfiguration(getTextTools().getColorManager(),
            getPreferenceStore(), null, TextMarkerPartitions.TM_PARTITIONING, false);
  }

  /*
   * @see
   * org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#setDocumentParticioner(org.eclipse
   * .jface.text.IDocument)
   */
  @Override
  protected void setDocumentParticioner(IDocument document) {
    getTextTools().setupDocumentPartitioner(document, TextMarkerPartitions.TM_PARTITIONING);
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#setPreferenceStore()
   */
  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(TextMarkerIdePlugin.getDefault().getPreferenceStore());
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#getTemplateAccess()
   */
  @Override
  protected ScriptTemplateAccess getTemplateAccess() {
    return TextMarkerTemplateAccess.getInstance();
  }

  private TextMarkerTextTools getTextTools() {
    return TextMarkerIdePlugin.getDefault().getTextTools();
  }
}
