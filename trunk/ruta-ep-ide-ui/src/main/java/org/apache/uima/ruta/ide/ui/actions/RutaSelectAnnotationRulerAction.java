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

package org.apache.uima.ruta.ide.ui.actions;

import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.ui.text.RutaCorrectionProcessor;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationAccessExtension;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.texteditor.AbstractMarkerAnnotationModel;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.AnnotationPreferenceLookup;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorExtension;
import org.eclipse.ui.texteditor.SelectMarkerRulerAction;

public class RutaSelectAnnotationRulerAction extends SelectMarkerRulerAction {

  private ITextEditor fTextEditor;

  private Position fPosition;

  private Annotation fAnnotation;

  private AnnotationPreferenceLookup fAnnotationPreferenceLookup;

  private IPreferenceStore fStore;

  private boolean fHasCorrection;

  private ResourceBundle fBundle;

  public RutaSelectAnnotationRulerAction(ResourceBundle bundle, String prefix, ITextEditor editor,
          IVerticalRulerInfo ruler) {
    super(bundle, prefix, editor, ruler);
    fBundle = bundle;
    fTextEditor = editor;

    fAnnotationPreferenceLookup = EditorsUI.getAnnotationPreferenceLookup();
    fStore = RutaIdeUIPlugin.getDefault().getPreferenceStore();

    // PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
    // IJavaHelpContextIds.JAVA_SELECT_MARKER_RULER_ACTION);
  }

  @Override
  public void run() {
    if (fStore.getBoolean(PreferenceConstants.EDITOR_ANNOTATION_ROLL_OVER))
      return;

    runWithEvent(null);
  }

  /*
   * @see org.eclipse.jface.action.IAction#runWithEvent(org.eclipse.swt.widgets.Event)
   * 
   * @since 3.2
   */
  @Override
  public void runWithEvent(Event event) {
    // if (fAnnotation instanceof
    // OverrideIndicatorManager.OverrideIndicator) {
    // ((OverrideIndicatorManager.OverrideIndicator)fAnnotation).open();
    // return;
    // }

    if (fHasCorrection) {
      ITextOperationTarget operation = (ITextOperationTarget) fTextEditor
              .getAdapter(ITextOperationTarget.class);
      final int opCode = ISourceViewer.QUICK_ASSIST;
      if (operation != null && operation.canDoOperation(opCode)) {
        fTextEditor.selectAndReveal(fPosition.getOffset(), fPosition.getLength());
        operation.doOperation(opCode);
      }
      return;
    }

    super.run();
  }

  @Override
  public void update() {
    findJavaAnnotation();
    setEnabled(true); // super.update() might change this later

    // if (fAnnotation instanceof
    // OverrideIndicatorManager.OverrideIndicator) {
    // initialize(fBundle,
    // "JavaSelectAnnotationRulerAction.OpenSuperImplementation.");
    // //$NON-NLS-1$
    // return;
    // }
    if (fHasCorrection) {
      // if (fAnnotation instanceof AssistAnnotation)
      // initialize(fBundle,
      // "JavaSelectAnnotationRulerAction.QuickAssist."); //$NON-NLS-1$
      // else
      initialize(fBundle, "JavaSelectAnnotationRulerAction.QuickFix."); //$NON-NLS-1$
      return;
    }

    initialize(fBundle, "JavaSelectAnnotationRulerAction.GotoAnnotation."); //$NON-NLS-1$;
    super.update();
  }

  private void findJavaAnnotation() {
    fPosition = null;
    fAnnotation = null;
    fHasCorrection = false;

    AbstractMarkerAnnotationModel model = getAnnotationModel();
    IAnnotationAccessExtension annotationAccess = getAnnotationAccessExtension();

    IDocument document = getDocument();
    if (model == null)
      return;

    boolean hasAssistLightbulb = false;
    // fStore.getBoolean(RutaPreferenceConstants.EDITOR_QUICKASSIST_LIGHTBULB);

    Iterator iter = model.getAnnotationIterator();
    int layer = Integer.MIN_VALUE;

    while (iter.hasNext()) {
      Annotation annotation = (Annotation) iter.next();
      if (annotation.isMarkedDeleted())
        continue;

      int annotationLayer = layer;
      if (annotationAccess != null) {
        annotationLayer = annotationAccess.getLayer(annotation);
        if (annotationLayer < layer)
          continue;
      }

      Position position = model.getPosition(annotation);
      if (!includesRulerLine(position, document))
        continue;

      boolean isReadOnly = fTextEditor instanceof ITextEditorExtension
              && ((ITextEditorExtension) fTextEditor).isEditorInputReadOnly();
      if (!isReadOnly && ((RutaCorrectionProcessor.hasCorrections(annotation)))) {
        fPosition = position;
        fAnnotation = annotation;
        fHasCorrection = true;
        layer = annotationLayer;
        continue;
      } else {
        AnnotationPreference preference = fAnnotationPreferenceLookup
                .getAnnotationPreference(annotation);
        if (preference == null)
          continue;

        String key = preference.getVerticalRulerPreferenceKey();
        if (key == null)
          continue;

        if (fStore.getBoolean(key)) {
          fPosition = position;
          fAnnotation = annotation;
          fHasCorrection = false;
          layer = annotationLayer;
        }
      }
    }
  }
}
