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

package org.apache.uima.textmarker.ide.ui.editor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerLanguageToolkit;
import org.apache.uima.textmarker.ide.core.codeassist.TextMarkerReferenceDeclarationVisitor;
import org.apache.uima.textmarker.ide.core.codeassist.TextMarkerReferenceVisitor;
import org.apache.uima.textmarker.ide.core.codeassist.TextMarkerRuleIdVisitor;
import org.apache.uima.textmarker.ide.core.codeassist.TextMarkerSelectionParser;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerCondition;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerRule;
import org.apache.uima.textmarker.ide.ui.TextMarkerPartitions;
import org.apache.uima.textmarker.ide.ui.TextMarkerPreferenceConstants;
import org.apache.uima.textmarker.ide.ui.actions.TextMarkerGenerateActionGroup;
import org.apache.uima.textmarker.ide.ui.text.TextMarkerPairMatcher;
import org.apache.uima.textmarker.ide.ui.text.folding.TextMarkerFoldingStructureProvider;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.internal.ui.editor.ToggleCommentAction;
import org.eclipse.dltk.ui.actions.IScriptEditorActionDefinitionIds;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.search.internal.ui.SearchPlugin;
import org.eclipse.search2.internal.ui.text.Highlighter;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.texteditor.TextOperationAction;

public class TextMarkerEditor extends ScriptEditor {

  protected class FormatElementAction extends Action implements IUpdate {

    /*
     * @since 3.2
     */
    FormatElementAction() {
      setEnabled(isEditorInputModifiable());
    }

    /*
     * @see org.eclipse.jface.action.IAction#run()
     */
    @Override
    public void run() {

      final ScriptSourceViewer viewer = (ScriptSourceViewer) getSourceViewer();
      if (viewer.isEditable()) {

        final Point selection = viewer.rememberSelection();
        try {
          viewer.setRedraw(false);

          final String type = TextUtilities.getContentType(viewer.getDocument(),
                  TextMarkerPartitions.TM_PARTITIONING, selection.x, true);
          if (type.equals(IDocument.DEFAULT_CONTENT_TYPE) && selection.y == 0) {

            try {
              final IModelElement element = getElementAt(selection.x, true);
              if (element != null && element.exists()) {

                final int kind = element.getElementType();
                if (kind == IModelElement.TYPE || kind == IModelElement.METHOD) {

                  final ISourceReference reference = (ISourceReference) element;
                  final ISourceRange range = reference.getSourceRange();

                  if (range != null) {
                    viewer.setSelectedRange(range.getOffset(), range.getLength());
                    viewer.doOperation(ISourceViewer.FORMAT);
                  }
                }
              }
            } catch (ModelException exception) {
              // Should not happen
            }
          } else {
            viewer.setSelectedRange(selection.x, 1);
            viewer.doOperation(ISourceViewer.FORMAT);
          }
        } catch (BadLocationException exception) {
          // Can not happen
        } finally {

          viewer.setRedraw(true);
          viewer.restoreSelection();
        }
      }
    }

    /*
     * @see org.eclipse.ui.texteditor.IUpdate#update()
     * 
     * @since 3.2
     */
    public void update() {
      setEnabled(isEditorInputModifiable());
    }
  }

  public static final String EDITOR_ID = "org.apache.uima.textmarker.ide.ui.editor.TextMarkerEditor";

  public static final String EDITOR_CONTEXT = "#TextMarkerEditorContext";

  public static final String RULER_CONTEXT = "#TextMarkerRulerContext";

  private IFoldingStructureProvider foldingProvider;

  private TextMarkerPairMatcher bracketMatcher;

  private void configureToggleCommentAction() {
    IAction action = getAction("ToggleComment"); //$NON-NLS-1$
    if (action instanceof ToggleCommentAction) {
      ISourceViewer sourceViewer = getSourceViewer();
      SourceViewerConfiguration configuration = getSourceViewerConfiguration();
      ((ToggleCommentAction) action).configure(sourceViewer, configuration);
    }
  }

  // public void dispose() {
  // super.dispose();
  // highlighter.dispose();
  // }

  @Override
  protected void initializeEditor() {
    super.initializeEditor();

    setEditorContextMenuId(EDITOR_CONTEXT);
    setRulerContextMenuId(RULER_CONTEXT);
  }

  @Override
  protected void createActions() {
    super.createActions();

    // Comment
    Action action = new TextOperationAction(DLTKEditorMessages.getBundleForConstructedKeys(),
            "Comment.", this, ITextOperationTarget.PREFIX); //$NON-NLS-1$
    action.setActionDefinitionId(IScriptEditorActionDefinitionIds.COMMENT);
    setAction("Comment", action); //$NON-NLS-1$
    markAsStateDependentAction("Comment", true); //$NON-NLS-1$

    // Uncomment
    action = new TextOperationAction(DLTKEditorMessages.getBundleForConstructedKeys(),
            "Uncomment.", this, ITextOperationTarget.STRIP_PREFIX); //$NON-NLS-1$
    action.setActionDefinitionId(IScriptEditorActionDefinitionIds.UNCOMMENT);
    setAction("Uncomment", action); //$NON-NLS-1$
    markAsStateDependentAction("Uncomment", true); //$NON-NLS-1$

    // Toggle comment
    action = new ToggleCommentAction(DLTKEditorMessages.getBundleForConstructedKeys(),
            "ToggleComment.", this); //$NON-NLS-1$
    action.setActionDefinitionId(IScriptEditorActionDefinitionIds.TOGGLE_COMMENT);
    setAction("ToggleComment", action); //$NON-NLS-1$
    markAsStateDependentAction("ToggleComment", true); //$NON-NLS-1$
    configureToggleCommentAction();

    action = new TextOperationAction(DLTKEditorMessages.getBundleForConstructedKeys(),
            "Format.", this, ISourceViewer.FORMAT); //$NON-NLS-1$
    action.setActionDefinitionId(IScriptEditorActionDefinitionIds.FORMAT);
    setAction("Format", action); //$NON-NLS-1$
    markAsStateDependentAction("Format", true); //$NON-NLS-1$
    markAsSelectionDependentAction("Format", true); //$NON-NLS-1$
    // PlatformUI.getWorkbench().getHelpSystem().setHelp(action,
    // IJavaHelpContextIds.FORMAT_ACTION);

    ActionGroup generateActions = new TextMarkerGenerateActionGroup(this,
            ITextEditorActionConstants.GROUP_EDIT);
//    getActionGroup().addGroup(generateActions);
//    fContextMenuGroup.addGroup(generateActions);
    // commented 090326->
    // action = new FormatElementAction();
    // action
    // .setActionDefinitionId(IScriptEditorActionDefinitionIds.QUICK_FORMAT);
    //		setAction("QuickFormat", action); //$NON-NLS-1$
    //		markAsStateDependentAction("QuickFormat", true); //$NON-NLS-1$ <-

    // action = new AddBlockCommentAction(DLTKEditorMessages
    //				.getBundleForConstructedKeys(), "AddBlockComment.", this); //$NON-NLS-1$
    // action
    // .setActionDefinitionId(IScriptEditorActionDefinitionIds.ADD_BLOCK_COMMENT);
    //		setAction("AddBlockComment", action); //$NON-NLS-1$
    //		markAsStateDependentAction("AddBlockComment", true); //$NON-NLS-1$
    //		markAsSelectionDependentAction("AddBlockComment", true); //$NON-NLS-1$
    // // PlatformUI.getWorkbench().getHelpSystem().setHelp(action,
    // // IDLTKJavaHelpContextIds.ADD_BLOCK_COMMENT_ACTION);
    //
    // action = new RemoveBlockCommentAction(DLTKEditorMessages
    //				.getBundleForConstructedKeys(), "RemoveBlockComment.", this); //$NON-NLS-1$
    // action
    // .setActionDefinitionId(IScriptEditorActionDefinitionIds.REMOVE_BLOCK_COMMENT);
    //		setAction("RemoveBlockComment", action); //$NON-NLS-1$
    //		markAsStateDependentAction("RemoveBlockComment", true); //$NON-NLS-1$
    //		markAsSelectionDependentAction("RemoveBlockComment", true); //$NON-NLS-1$
    // PlatformUI.getWorkbench().getHelpSystem().setHelp(action,
    // IJavaHelpContextIds.REMOVE_BLOCK_COMMENT_ACTION);

    // action = new IndentAction(DLTKEditorMessages
    //				.getBundleForConstructedKeys(), "Indent.", this, false); //$NON-NLS-1$
    // action.setActionDefinitionId(IScriptEditorActionDefinitionIds.INDENT);
    //		setAction("Indent", action); //$NON-NLS-1$
    //		markAsStateDependentAction("Indent", true); //$NON-NLS-1$
    //		markAsSelectionDependentAction("Indent", true); //$NON-NLS-1$
    // // PlatformUI.getWorkbench().getHelpSystem().setHelp(action,
    // // IJavaHelpContextIds.INDENT_ACTION);

  }

  final static String[] properties = new String[] {
      TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS,
      TextMarkerPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST,
      TextMarkerPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST, };

  private Highlighter highlighter;

  private HashMap<Annotation, Position> myAnnotations;

  @Override
  protected String[] getFoldingEventPreferenceKeys() {
    return properties;
  }

  // protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
  // String property = event.getProperty();
  // try {
  // ISourceViewer sourceViewer = getSourceViewer();
  // if (sourceViewer == null) {
  // return;
  // }
  // if (TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS
  // .equals(property)
  // || TextMarkerPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES
  // .equals(property)
  // || TextMarkerPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST
  // .equals(property)
  // || TextMarkerPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST
  // .equals(property)
  // || PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT
  // .equals(property)) {
  //
  // if (sourceViewer instanceof ProjectionViewer) {
  // fProjectionModelUpdater.initialize();
  // }
  // return;
  // }
  // } finally {
  // super.handlePreferenceStoreChanged(event);
  // }
  // }

  @Override
  public IPreferenceStore getScriptPreferenceStore() {
    return TextMarkerIdePlugin.getDefault().getPreferenceStore();
  }

  @Override
  public ScriptTextTools getTextTools() {
    return TextMarkerIdePlugin.getDefault().getTextTools();
  }

  @Override
  protected ScriptOutlinePage doCreateOutlinePage() {
    return new TextMarkerOutlinePage(this, TextMarkerIdePlugin.getDefault().getPreferenceStore());
  }

  @Override
  protected void connectPartitioningToElement(IEditorInput input, IDocument document) {
    if (document instanceof IDocumentExtension3) {
      IDocumentExtension3 doc = (IDocumentExtension3) document;
      if (doc.getDocumentPartitioner(TextMarkerPartitions.TM_PARTITIONING) == null) {
        IDocumentSetupParticipant participant = new TextMarkerDocumentSetupParticipant();
        participant.setup(document);
      }
    }
  }

  @Override
  protected IFoldingStructureProvider getFoldingStructureProvider() {
    if (foldingProvider == null) {
      foldingProvider = new TextMarkerFoldingStructureProvider();
    }

    return foldingProvider;
  }

  @Override
  protected FoldingActionGroup createFoldingActionGroup() {
    return new FoldingActionGroup(this, getViewer(), TextMarkerIdePlugin.getDefault()
            .getPreferenceStore());
  }

  @Override
  public String getEditorId() {
    return EDITOR_ID;
  }

  protected void selectionChanged() {

    if (getSelectionProvider() == null)
      return;

    super.selectionChanged();

    if (myAnnotations != null && !myAnnotations.isEmpty()) {
      removeAnnotations(myAnnotations.keySet());
    }

    TextMarkerSelectionParser parser = new TextMarkerSelectionParser();
    ISourceModule unit = (ISourceModule) getInputModelElement();
    ModuleDeclaration parsed = parser.parse(unit);
    ISourceViewer sourceViewer = getSourceViewer();
    StyledText styledText = sourceViewer.getTextWidget();
    int caret = 0;
    if (sourceViewer instanceof ITextViewerExtension5) {
      ITextViewerExtension5 extension = (ITextViewerExtension5) sourceViewer;
      caret = extension.widgetOffset2ModelOffset(styledText.getCaretOffset());
    } else {
      int offset = sourceViewer.getVisibleRegion().getOffset();
      caret = offset + styledText.getCaretOffset();
    }
    TextMarkerReferenceVisitor visitor1 = new TextMarkerReferenceVisitor(caret);
    ASTNode node = null;
    try {
      parsed.traverse(visitor1);
    } catch (Exception e) {
    }
    node = visitor1.getResult();
    if (node == null) {
      TextMarkerReferenceDeclarationVisitor visitor2 = new TextMarkerReferenceDeclarationVisitor(
              caret);
      try {
        parsed.traverse(visitor2);
      } catch (Exception e) {
      }
      node = visitor2.getResult();
    }
    if (node != null) {
      ReferenceFinder refFinder = new ReferenceFinder(node);
      try {
        parsed.traverse(refFinder);
      } catch (Exception e) {
      }
      List<ASTNode> result = refFinder.getResult();
      myAnnotations = new HashMap<Annotation, Position>(result.size());
      for (ASTNode each : result) {
        Annotation annotation = new Annotation(SearchPlugin.SEARCH_ANNOTATION_TYPE, true, null);
        Position position = null;
        int sourceStart = each.sourceStart();
        int sourceEnd = each.sourceEnd();
        if (each instanceof TextMarkerAction) {
          TextMarkerAction e = (TextMarkerAction) each;
          sourceStart = e.getNameStart();
          sourceEnd = e.getNameEnd();
        } else if (each instanceof TextMarkerCondition) {
          TextMarkerCondition e = (TextMarkerCondition) each;
          sourceStart = e.getNameStart();
          sourceEnd = e.getNameEnd();
        }
        position = new Position(sourceStart, sourceEnd - sourceStart);
        myAnnotations.put(annotation, position);
      }
      addAnnotations(myAnnotations);
    }

  }

  public void highlightElement(int id) {
    if (myAnnotations != null && !myAnnotations.isEmpty()) {
      removeAnnotations(myAnnotations.keySet());
    }
    
    TextMarkerSelectionParser parser = new TextMarkerSelectionParser();
    ISourceModule unit = (ISourceModule) getInputModelElement();
    ModuleDeclaration parsed = parser.parse(unit);
    TextMarkerRuleIdVisitor visitor = new TextMarkerRuleIdVisitor(id);
    try {
      parsed.traverse(visitor);
    } catch (Exception e) {
    }
    TextMarkerRule rule = visitor.getResult();
    myAnnotations = new HashMap<Annotation, Position>();
    if(rule != null) {
      Annotation annotation = new Annotation(SearchPlugin.SEARCH_ANNOTATION_TYPE, true, null);
      int sourceStart = rule.sourceStart();
      int sourceEnd = rule.sourceEnd();
      Position position = new Position(sourceStart, sourceEnd - sourceStart);
      getSourceViewer().revealRange(sourceStart, sourceEnd - sourceStart);
      myAnnotations.put(annotation, position);
    }
    addAnnotations(myAnnotations);
  }

  
  
  private void removeAnnotations(Collection<Annotation> annotations) {
    IAnnotationModel model = getDocumentProvider().getAnnotationModel(getEditorInput());
    for (Annotation annotation : annotations) {
      model.removeAnnotation(annotation);
    }
  }

  private void addAnnotations(Map<Annotation, Position> annotationToPositionMap) {
    IAnnotationModel model = getDocumentProvider().getAnnotationModel(getEditorInput());
    for (Annotation a : annotationToPositionMap.keySet()) {
      Position p = annotationToPositionMap.get(a);
      model.addAnnotation(a, p);
    }
  }

  @Override
  protected void initializeKeyBindingScopes() {
    setKeyBindingScopes(new String[] { "org.apache.uima.textmarker.ide.ui.textMarkerEditorScope" }); //$NON-NLS-1$
  }

  @Override
  public IDLTKLanguageToolkit getLanguageToolkit() {
    return TextMarkerLanguageToolkit.getDefault();
  }

  @Override
  public String getCallHierarchyID() {
    return "org.eclipse.dltk.callhierarchy.view";
  }

  /**
   * Jumps to the matching bracket.
   */
  @Override
  public void gotoMatchingBracket() {
    ISourceViewer sourceViewer = getSourceViewer();
    IDocument document = sourceViewer.getDocument();
    if (document == null)
      return;

    IRegion selection = getSignedSelection(sourceViewer);

    int selectionLength = Math.abs(selection.getLength());
    if (selectionLength > 1) {
      setStatusLineErrorMessage("No bracket selected");
      sourceViewer.getTextWidget().getDisplay().beep();
      return;
    }

    // #26314
    int sourceCaretOffset = selection.getOffset() + selection.getLength();
    if (isSurroundedByBrackets(document, sourceCaretOffset))
      sourceCaretOffset -= selection.getLength();

    IRegion region = bracketMatcher.match(document, sourceCaretOffset);
    if (region == null) {
      setStatusLineErrorMessage("No matching bracket found");
      sourceViewer.getTextWidget().getDisplay().beep();
      return;
    }

    int offset = region.getOffset();
    int length = region.getLength();

    if (length < 1)
      return;

    int anchor = bracketMatcher.getAnchor();
    // http://dev.eclipse.org/bugs/show_bug.cgi?id=34195
    int targetOffset = (ICharacterPairMatcher.RIGHT == anchor) ? offset + 1 : offset + length;

    boolean visible = false;
    if (sourceViewer instanceof ITextViewerExtension5) {
      ITextViewerExtension5 extension = (ITextViewerExtension5) sourceViewer;
      visible = (extension.modelOffset2WidgetOffset(targetOffset) > -1);
    } else {
      IRegion visibleRegion = sourceViewer.getVisibleRegion();
      // http://dev.eclipse.org/bugs/show_bug.cgi?id=34195
      visible = (targetOffset >= visibleRegion.getOffset() && targetOffset <= visibleRegion
              .getOffset() + visibleRegion.getLength());
    }

    if (!visible) {
      setStatusLineErrorMessage("Matching bracket is outside selected element");
      sourceViewer.getTextWidget().getDisplay().beep();
      return;
    }

    if (selection.getLength() < 0)
      targetOffset -= selection.getLength();

    sourceViewer.setSelectedRange(targetOffset, selection.getLength());
    sourceViewer.revealRange(targetOffset, selection.getLength());
  }

  @Override
  protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
    bracketMatcher = new TextMarkerPairMatcher(BRACKETS, this);
    support.setCharacterPairMatcher(bracketMatcher);
    support.setMatchingCharacterPainterPreferenceKeys(MATCHING_BRACKETS, MATCHING_BRACKETS_COLOR);

    super.configureSourceViewerDecorationSupport(support);
  }

  
}
