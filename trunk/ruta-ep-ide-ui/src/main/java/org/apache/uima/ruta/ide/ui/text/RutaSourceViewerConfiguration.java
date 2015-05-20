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

package org.apache.uima.ruta.ide.ui.text;

import org.apache.uima.ruta.ide.ui.RutaPartitions;
import org.apache.uima.ruta.ide.ui.hierarchy.RutaHierarchyInformationControl;
import org.apache.uima.ruta.ide.ui.text.completion.RutaContentAssistPreference;
import org.apache.uima.ruta.ide.ui.text.completion.RutaScriptCompletionProcessor;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.internal.ui.text.ScriptElementProvider;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptPresentationReconciler;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.dltk.ui.text.completion.ContentAssistProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.ITextEditor;

public class RutaSourceViewerConfiguration extends ScriptSourceViewerConfiguration {

  private RutaTextTools fTextTools;

  private RutaCodeScanner fCodeScanner;

  private AbstractScriptScanner fStringScanner;

  private AbstractScriptScanner fCommentScanner;

  public RutaSourceViewerConfiguration(IColorManager colorManager,
          IPreferenceStore preferenceStore, ITextEditor editor, String partitioning) {
    super(colorManager, preferenceStore, editor, partitioning);
  }

  @Override
  public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
    return RutaPartitions.RUTA_PARTITION_TYPES;
  }

  @Override
  public String[] getIndentPrefixes(ISourceViewer sourceViewer, String contentType) {
    return new String[] { "\t", "    " };
  }

  /*
   * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getTabWidth(org
   * .eclipse.jface.text.source.ISourceViewer)
   */
  @Override
  public int getTabWidth(ISourceViewer sourceViewer) {
    if (fPreferenceStore == null)
      return super.getTabWidth(sourceViewer);
    return fPreferenceStore.getInt(CodeFormatterConstants.FORMATTER_TAB_SIZE);
  }

  @Override
  protected void initializeScanners() {
    Assert.isTrue(isNewSetup());
    fCodeScanner = new RutaCodeScanner(getColorManager(), fPreferenceStore);
    fStringScanner = new RutaStringScanner(getColorManager(), fPreferenceStore);
    fCommentScanner = createCommentScanner(RutaColorConstants.RUTA_SINGLE_LINE_COMMENT,
            RutaColorConstants.RUTA_TODO_TAG);
  }

  @Override
  protected void alterContentAssistant(ContentAssistant assistant) {
    // IDocument.DEFAULT_CONTENT_TYPE
    IContentAssistProcessor scriptProcessor = new RutaScriptCompletionProcessor(getEditor(),
            assistant, IDocument.DEFAULT_CONTENT_TYPE);
    assistant.setContentAssistProcessor(scriptProcessor, IDocument.DEFAULT_CONTENT_TYPE);

    ContentAssistProcessor singleLineProcessor = new RutaScriptCompletionProcessor(getEditor(),
            assistant, RutaPartitions.RUTA_COMMENT);
    assistant.setContentAssistProcessor(singleLineProcessor, RutaPartitions.RUTA_COMMENT);

    ContentAssistProcessor stringProcessor = new RutaScriptCompletionProcessor(getEditor(),
            assistant, RutaPartitions.RUTA_STRING);
    assistant.setContentAssistProcessor(stringProcessor, RutaPartitions.RUTA_STRING);
  }

  private boolean isNewSetup() {
    return fTextTools == null;
  }

  protected RuleBasedScanner getStringScanner() {
    return fStringScanner;
  }

  @Override
  public String getCommentPrefix() {
    return "//";
  }

  protected RuleBasedScanner getCommentScanner() {
    return fCommentScanner;
  }

  @Override
  public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
    PresentationReconciler reconciler = new ScriptPresentationReconciler();
    reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

    DefaultDamagerRepairer dr = new DefaultDamagerRepairer(this.fCodeScanner);
    reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
    reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

    dr = new DefaultDamagerRepairer(getStringScanner());
    reconciler.setDamager(dr, RutaPartitions.RUTA_STRING);
    reconciler.setRepairer(dr, RutaPartitions.RUTA_STRING);

    dr = new DefaultDamagerRepairer(getCommentScanner());
    reconciler.setDamager(dr, RutaPartitions.RUTA_COMMENT);
    reconciler.setRepairer(dr, RutaPartitions.RUTA_COMMENT);

    return reconciler;
  }

  @Override
  public void handlePropertyChangeEvent(PropertyChangeEvent event) {
    Assert.isTrue(isNewSetup());
    if (fCodeScanner.affectsBehavior(event))
      fCodeScanner.adaptToPreferenceChange(event);
    if (fStringScanner.affectsBehavior(event))
      fStringScanner.adaptToPreferenceChange(event);
    if (fCommentScanner.affectsBehavior(event)) {
      fCommentScanner.adaptToPreferenceChange(event);
    }
  }

  @Override
  public boolean affectsTextPresentation(PropertyChangeEvent event) {
    return fCodeScanner.affectsBehavior(event) || fStringScanner.affectsBehavior(event)
            || fCommentScanner.affectsBehavior(event);
  }

  private IInformationControlCreator getHierarchyPresenterControlCreator(ISourceViewer sourceViewer) {
    return new IInformationControlCreator() {
      public IInformationControl createInformationControl(Shell parent) {
        int shellStyle = SWT.RESIZE;
        int treeStyle = SWT.V_SCROLL | SWT.H_SCROLL;
        return new RutaHierarchyInformationControl(parent, shellStyle, treeStyle);
      }
    };
  }

  @Override
  public IInformationPresenter getHierarchyPresenter(ScriptSourceViewer sourceViewer,
          boolean doCodeResolve) {
    // Do not create hierarchy presenter if there's no CU.
    if (getEditor() != null && getEditor().getEditorInput() != null
            && EditorUtility.getEditorInputModelElement(getEditor(), true) == null)
      return null;

    InformationPresenter presenter = new InformationPresenter(
            getHierarchyPresenterControlCreator(sourceViewer));
    presenter.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
    presenter.setAnchor(AbstractInformationControlManager.ANCHOR_GLOBAL);
    IInformationProvider provider = new ScriptElementProvider(getEditor(), doCodeResolve);
    presenter.setInformationProvider(provider, IDocument.DEFAULT_CONTENT_TYPE);

    presenter.setSizeConstraints(50, 20, true, false);
    return presenter;
  }

  public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
    if (!fPreferenceStore
            .getBoolean(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_HYPERLINKS_ENABLED))
      return null;

    IHyperlinkDetector[] inheritedDetectors = super.getHyperlinkDetectors(sourceViewer);

    if (getEditor() == null) {
      return inheritedDetectors;
    }

    int inheritedDetectorsLength = inheritedDetectors != null ? inheritedDetectors.length : 0;
    IHyperlinkDetector[] detectors = new IHyperlinkDetector[inheritedDetectorsLength + 1];

    for (int i = 0; i < inheritedDetectorsLength; i++)
      detectors[i] = inheritedDetectors[i];
    detectors[inheritedDetectorsLength] = new ExternalTypeHyperlinkDetector(getEditor());

    return detectors;
  }

  @Override
  protected IInformationControlCreator getOutlinePresenterControlCreator(
          ISourceViewer sourceViewer, final String commandId) {
    return new IInformationControlCreator() {
      public IInformationControl createInformationControl(Shell parent) {
        int shellStyle = SWT.RESIZE;
        int treeStyle = SWT.V_SCROLL | SWT.H_SCROLL;
        return new RutaOutlineInformationControl(parent, shellStyle, treeStyle, commandId);
      }
    };
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAutoEditStrategies
   * (org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
   */
  @Override
  public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
    // TODO: check contentType. think, do we really need it? :)
    String partitioning = getConfiguredDocumentPartitioning(sourceViewer);
    // return new IAutoEditStrategy[] { new DefaultAutoIndentStrategy() };
    return new IAutoEditStrategy[] { new RutaAutoEditStrategy(fPreferenceStore, partitioning) };
  }

  @Override
  protected void initializeQuickOutlineContexts(InformationPresenter presenter,
          IInformationProvider provider) {
    presenter.setInformationProvider(provider, RutaPartitions.RUTA_COMMENT);
    presenter.setInformationProvider(provider, RutaPartitions.RUTA_STRING);
  }

  @Override
  protected ContentAssistPreference getContentAssistPreference() {
    return RutaContentAssistPreference.getDefault();
  }

}
