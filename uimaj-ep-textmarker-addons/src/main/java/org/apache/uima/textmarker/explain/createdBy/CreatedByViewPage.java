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

package org.apache.uima.textmarker.explain.createdBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.FeatureStructureSelection;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.apache.uima.caseditor.editor.ICasDocumentListener;
import org.apache.uima.caseditor.editor.ICasEditorInputListener;
import org.apache.uima.textmarker.addons.TextMarkerAddonsPlugin;
import org.apache.uima.textmarker.engine.TextMarkerEngine;
import org.apache.uima.textmarker.explain.ExplainConstants;
import org.apache.uima.textmarker.explain.ExplainUtils;
import org.apache.uima.textmarker.explain.apply.ApplyView;
import org.apache.uima.textmarker.explain.rulelist.RuleListView;
import org.apache.uima.textmarker.explain.selection.ExplainSelectionView;
import org.apache.uima.textmarker.explain.tree.ExplainTree;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.textmarker.ide.ui.editor.TextMarkerEditor;
import org.apache.uima.textmarker.testing.ui.views.fn.FalseNegativeView;
import org.apache.uima.textmarker.testing.ui.views.fp.FalsePositiveView;
import org.apache.uima.textmarker.testing.ui.views.tp.TruePositiveView;
import org.apache.uima.textmarker.visitor.CreatedByVisitor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

public class CreatedByViewPage extends Page implements ISelectionListener, ICasEditorInputListener, IDoubleClickListener {

  public static final String TM_RULE = "TMRule";

  protected TreeViewer viewer;

  protected int current = 0;

  protected Map<String, Image> images;

  protected AnnotationEditor editor;

  protected ICasDocument document;

  public CreatedByViewPage(AnnotationEditor editor) {
    super();
    this.editor = editor;
    this.document = editor.getDocument();
  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/tm_obj.png");
    image = desc.createImage();
    name = TM_RULE;
    images.put(name, image);

  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
  }

  @Override
  public Control getControl() {
    return viewer.getControl();
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  @Override
  public void init(IPageSite pageSite) {
    super.init(pageSite);
  }

  public TreeViewer getTreeViewer() {
    return viewer;
  }

  @Override
  public void createControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setLabelProvider(new CreatedByLabelProvider(this));
    viewer.setContentProvider(new CreatedByContentProvider());
    viewer.setInput(null);
    viewer.addDoubleClickListener(this);
    getSite().setSelectionProvider(viewer);
    getSite().getPage().addSelectionListener(this);
    editor.addCasEditorInputListener(this);
    viewer.refresh();
  }

  public void doubleClick(DoubleClickEvent event) {
    ISelection selection = event.getSelection();
    if(!selection.isEmpty() && selection instanceof TreeSelection) {
      TreeSelection s = (TreeSelection) selection;
      FeatureStructure fs = (FeatureStructure) s.getFirstElement();
      Type t = fs.getType();
      Feature featureId = t.getFeatureByBaseName(CreatedByVisitor.FEATURE_ID);
      Feature featureScript = t.getFeatureByBaseName(CreatedByVisitor.FEATURE_SCRIPT);
      int id = fs.getIntValue(featureId);
      String script = fs.getStringValue(featureScript);
      IEditorInput editorInput = editor.getEditorInput();
      if(editorInput instanceof FileEditorInput) {
        FileEditorInput fei = (FileEditorInput) editorInput;
        IPath path = fei.getPath();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot workspaceRoot = workspace.getRoot();
        IFile iFile = workspaceRoot.getFileForLocation(path);
        IProject project = iFile.getProject();
        IScriptProject scriptProject = DLTKCore.create(project);
        List<IFolder> allScriptFolders;
        try {
          allScriptFolders = TextMarkerProjectUtils.getAllScriptFolders(scriptProject);
          List<String> folders = TextMarkerProjectUtils.getFolderLocations(allScriptFolders);
          String locate = TextMarkerEngine.locate(script, folders.toArray(new String[0]), ".tm");
          IPath locatedPath = new Path(locate);
          ExplainUtils.openInTextMarkerEditor(locatedPath, id);
        } catch (CoreException e) {
          TextMarkerAddonsPlugin.error(e);
        }
      }
    }
  }

  

  
  @Override
  public void dispose() {
    super.dispose();
    getSite().getPage().removeSelectionListener(this);
    editor.removeCasEditorInputListener(this);
    if (images != null) {
      for (Image each : images.values()) {
        each.dispose();
      }
    }
  }

  public void mouseDown(final MouseEvent event) {

  }

  public void mouseUp(final MouseEvent event) {

  }

  public void mouseDoubleClick(final MouseEvent event) {

  }

  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof IStructuredSelection) {
      FeatureStructureSelection fsSelection = new FeatureStructureSelection(
              (IStructuredSelection) selection);
      if (fsSelection.isEmpty()) {
        return;
      }
      FeatureStructure featureStructure = fsSelection.toList().get(0);
      if (featureStructure instanceof AnnotationFS) {
        CAS cas = document.getCAS();
        Type t = cas.getTypeSystem().getType(CreatedByVisitor.TYPE);
        Feature featureAnnotation = t.getFeatureByBaseName(CreatedByVisitor.FEATURE_ANNOTATION);
        FSIterator<FeatureStructure> iterator = cas.getIndexRepository().getAllIndexedFS(t);
        while (iterator.hasNext()) {
          FeatureStructure fs = (FeatureStructure) iterator.next();
          AnnotationFS afs = (AnnotationFS) fs.getFeatureValue(featureAnnotation);
          if (afs.equals(featureStructure)) {
            viewer.setInput(fs);
            return;
          }
        }
        viewer.setInput(null);
      }
    }
  }

  public void casDocumentChanged(IEditorInput oldInput, ICasDocument oldDocument,
          IEditorInput newInput, ICasDocument newDocument) {
  }
}
