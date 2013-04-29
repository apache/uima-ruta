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

package org.apache.uima.textmarker.ide.ui.text;

import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

public class SimpleTextMarkerSourceViewerConfiguration extends TextMarkerSourceViewerConfiguration {

  private boolean fConfigureFormatter;

  public SimpleTextMarkerSourceViewerConfiguration(IColorManager colorManager,
          IPreferenceStore preferenceStore, ITextEditor editor, String partitioning,
          boolean configureFormatter) {
    super(colorManager, preferenceStore, editor, partitioning);
    fConfigureFormatter = configureFormatter;
  }

  @Override
  public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
    return null;
  }

  @Override
  public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
    return null;
  }

  @Override
  public IAnnotationHover getOverviewRulerAnnotationHover(ISourceViewer sourceViewer) {
    return null;
  }

  @Override
  public int[] getConfiguredTextHoverStateMasks(ISourceViewer sourceViewer, String contentType) {
    return null;
  }

  @Override
  public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType, int stateMask) {
    return null;
  }

  @Override
  public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
    return null;
  }

  @Override
  public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
    if (fConfigureFormatter)
      return super.getContentFormatter(sourceViewer);
    else
      return null;
  }

  @Override
  public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer) {
    return null;
  }

  @Override
  public IInformationPresenter getInformationPresenter(ISourceViewer sourceViewer) {
    return null;
  }

  public IInformationPresenter getOutlinePresenter(ISourceViewer sourceViewer, boolean doCodeResolve) {
    return null;
  }

  public IInformationPresenter getHierarchyPresenter(ISourceViewer sourceViewer,
          boolean doCodeResolve) {
    return null;
  }

  @Override
  public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
    return null;
  }
}
