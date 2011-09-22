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

package org.apache.uima.cev.editor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cev.CEVPlugin;
import org.apache.uima.cev.artifactViewer.ArtifactModifier;
import org.apache.uima.cev.data.CEVData;
import org.apache.uima.cev.data.CEVDocument;
import org.apache.uima.cev.data.ICEVAnnotationListener;
import org.apache.uima.cev.extension.ICEVArtifactViewer;
import org.apache.uima.cev.extension.ICEVArtifactViewerFactory;
import org.apache.uima.cev.extension.ICEVEditor;
import org.apache.uima.cev.extension.ICEVEditorFactory;
import org.apache.uima.cev.extension.ICEVSearchStrategy;
import org.apache.uima.cev.extension.ICEVView;
import org.apache.uima.cev.extension.ICEVViewFactory;
import org.apache.uima.cev.preferences.CEVPreferenceConstants;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.tools.stylemap.StyleMapEntry;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XmlCasSerializer;
import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.xml.sax.SAXException;

public class CEVViewer extends MultiPageEditorPart implements IResourceChangeListener,
        ICEVAnnotationListener, MouseListener, SelectionListener {

  private CTabFolder[] folderArray;

  private CEVDocument casDocument;

  private CEVData activeCASData;

  private boolean dirty;

  private Map<Class<?>, ICEVView> views;

  private Map<Class<?>, ICEVEditor> editors;

  private Map<Class<?>, ICEVViewFactory> viewAdapter;

  private Map<Class<?>, ICEVEditorFactory> editorAdapter;

  private List<ICEVSearchStrategy> searchStrategies;

  private List<ICEVArtifactViewerFactory> artifactViewerFactories;

  private FileEditorInput inputFile;

  private Map<Integer, List<ICEVArtifactViewer>> casViews;

  private List<Type> initialVisibleTypes;

  public CEVViewer() {
    super();
    ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    dirty = false;
    views = new HashMap<Class<?>, ICEVView>();
    editors = new HashMap<Class<?>, ICEVEditor>();
    viewAdapter = CEVPlugin.getViewAdapters();
    editorAdapter = CEVPlugin.getEditorAdapters();
    searchStrategies = CEVPlugin.getSearchStrategies();
    artifactViewerFactories = CEVPlugin.getArtifactViewerFactories();
  }

  @Override
  protected void createPages() {
    if (casDocument == null) {
      return;
    }
    folderArray = new CTabFolder[casDocument.count()];
    casViews = new HashMap<Integer, List<ICEVArtifactViewer>>();
    int index = 0;
    for (CEVData each : casDocument.getCASData()) {
      CTabFolder folder = new CTabFolder(getContainer(), SWT.BOTTOM | SWT.FLAT);
      folder.addSelectionListener(this);
      addPage(index, folder);
      setPageText(index, each.getViewName());
      folderArray[index] = folder;
      ArrayList<ICEVArtifactViewer> artifactViewerListOfFolder = new ArrayList<ICEVArtifactViewer>();
      casViews.put(index, artifactViewerListOfFolder);
      for (ICEVArtifactViewerFactory eachFactory : artifactViewerFactories) {
        if (eachFactory.isAble(each.getCAS())) {
          CTabItem tabItem = new CTabItem(folder, SWT.NONE);
          try {
            ICEVArtifactViewer artifactViewer = eachFactory.createArtifactViewer(this, tabItem,
                    each);
            artifactViewerListOfFolder.add(artifactViewer);
          } catch (PartInitException e) {
            CEVPlugin.error(e);
          }
        }
      }
      folder.setSelection(0);
      index++;
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.MultiPageEditorPart#dispose()
   */
  @Override
  public void dispose() {
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    if (casViews != null) {
      for (List<ICEVArtifactViewer> eachList : casViews.values()) {
        for (ICEVArtifactViewer each : eachList) {
          if (each != null) {
            each.dispose();
          }
        }
      }
      casViews.clear();
    }
    if (casDocument != null) {
      casDocument.dispose();
      casDocument = null;
    }
    super.dispose();
  }

  @Override
  public void doSave(IProgressMonitor monitor) {
    CAS newCas = casDocument.getMainCas();
    List<ArtifactModifier> modifiers = new ArrayList<ArtifactModifier>();
    for (Integer eachIndex : casViews.keySet()) {
      for (ICEVArtifactViewer each : casViews.get(eachIndex)) {
        if (each instanceof ArtifactModifier) {
          modifiers.add((ArtifactModifier) each);
        }
      }
    }
    if (!modifiers.isEmpty()) {
      try {
        newCas = casDocument.createCas();
      } catch (ResourceInitializationException e) {
        CEVPlugin.error(e);
      } catch (InvalidXMLException e) {
        CEVPlugin.error(e);
      }

      org.apache.uima.util.CasCopier.copyCas(casDocument.getMainCas(), newCas, false);
      for (Integer eachIndex : casViews.keySet()) {
        for (ICEVArtifactViewer each : casViews.get(eachIndex)) {
          if (each instanceof ArtifactModifier) {
            ArtifactModifier am = (ArtifactModifier) each;
            am.modifyCas(casDocument, newCas, eachIndex);
          }
        }
      }
    }
    try {
      IFile iFile = ((FileEditorInput) getEditorInput()).getFile();
      File file = iFile.getLocation().toFile();
      XmlCasSerializer.serialize(newCas, new FileOutputStream(file));
      iFile.getParent().refreshLocal(Resource.DEPTH_INFINITE, new NullProgressMonitor());
    } catch (Exception e) {
      CEVPlugin.error(e);
    }
    setDirty(false);

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.EditorPart#doSaveAs()
   */
  @Override
  public void doSaveAs() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.MultiPageEditorPart#init(org.eclipse.ui.IEditorSite,
   * org.eclipse.ui.IEditorInput)
   */
  @Override
  public void init(IEditorSite site, final IEditorInput editorInput) throws PartInitException {
    if (!(editorInput instanceof FileEditorInput))
      throw new PartInitException("Invalid Input: Must be IFileEditorInput");
    super.init(site, editorInput);

    setPartName(((FileEditorInput) editorInput).getName());

    createCAS((FileEditorInput) editorInput);
    if (casDocument != null) {
      for (CEVData casData : casDocument.getCASData()) {
        casData.addAnnotationListener(CEVViewer.this);

      }
    }

    initialVisibleTypes = new ArrayList<Type>();
    if (casDocument != null) {
      Map<String, StyleMapEntry> styleMap = casDocument.getStyleMap();
      if (styleMap != null) {
        for (Entry<String, StyleMapEntry> each : styleMap.entrySet()) {
          Type type = casDocument.getMainCas().getTypeSystem().getType(each.getKey());
          if (type != null) {
            initialVisibleTypes.add(type);
          }
        }
      }
    }
  }

  private void closeEditor() {
    getSite().getPage().closeEditor(CEVViewer.this, false);
  }

  private void createCAS(FileEditorInput inputFile) throws PartInitException {
    this.inputFile = inputFile;
    try {
      IFile file = null;
      for (ICEVSearchStrategy each : searchStrategies) {
        try {
          file = each.searchDescriptor(inputFile.getFile());
        } catch (Exception e) {
        }
        if (file != null) {
          break;
        }
      }
      if (file != null) {
        casDocument = new CEVDocument(file, inputFile.getFile());
      }
    } catch (IllegalArgumentException e) {
      throw new PartInitException(e.getMessage());
    } catch (IOException e) {
      throw new PartInitException(e.getMessage());
    } catch (InvalidXMLException e) {
      throw new PartInitException(e.getMessage());
    } catch (SAXException e) {
      throw new PartInitException(e.getMessage());
    } catch (ResourceInitializationException e) {
      throw new PartInitException(e.getMessage());
    }
  }

  /*
   * (non-Javadoc) Method declared on IEditorPart.
   */
  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.MultiPageEditorPart#isDirty()
   */
  @Override
  public boolean isDirty() {
    return dirty;
  }

  public void setDirty(boolean dirty) {
    this.dirty = dirty;
    firePropertyChange(PROP_DIRTY);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.MultiPageEditorPart#pageChange(int)
   */
  @Override
  protected void pageChange(int newPageIndex) {
    super.pageChange(newPageIndex);

    activeCASData = casDocument.getCASData(newPageIndex);

    for (List<ICEVArtifactViewer> eachList : casViews.values()) {
      for (ICEVArtifactViewer each : eachList) {
        each.viewChanged(activeCASData);
      }
    }
    for (ICEVView each : new ArrayList<ICEVView>(views.values())) {
      each.viewChanged(newPageIndex);
    }
    for (ICEVEditor each : editors.values()) {
      each.viewChanged(newPageIndex);
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org
   * .eclipse.core.resources.IResourceChangeEvent)
   */
  public void resourceChanged(final IResourceChangeEvent event) {
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    boolean reload = store.getBoolean(CEVPreferenceConstants.P_AUTO_REFRESH);
    if (reload) {
      IResourceDelta findInDelta = findInDelta(event.getDelta(), inputFile.getFile().getFullPath());
      if (findInDelta != null) {

        final CEVViewer t = this;
        Display display = Display.getCurrent();
        if (display == null) {
          // [FIXME] get correct display or replace code

          display = getEditorSite().getWorkbenchWindow().getShell().getDisplay();
          // display = activeText.getDisplay();
          if (display == null) {
            display = getActiveEditor().getSite().getShell().getDisplay();
          }
        }
        display.asyncExec(new Runnable() {
          public void run() {

            int page = getActivePage();
            if (casDocument != null) {
              for (CEVData casData : casDocument.getCASData()) {
                casData.removeAnnotationListener(t);
              }
            }
            try {
              init(getEditorSite(), inputFile);
            } catch (PartInitException e) {
              CEVPlugin.error(e);
            }

            for (ICEVView each : views.values()) {
              each.casChanged(casDocument);
            }
            for (ICEVEditor each : editors.values()) {
              each.casChanged(casDocument);
            }
            pageChange(page);
          }
        });
      }
    }
  }

  private IResourceDelta findInDelta(IResourceDelta delta, IPath target) {
    if (delta.getFullPath().equals(target)) {
      return delta;
    } else {
      for (IResourceDelta each : delta.getAffectedChildren(IResourceDelta.CHANGED)) {
        IResourceDelta findInDelta = findInDelta(each, target);
        if (findInDelta != null) {
          return findInDelta;
        }
      }
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.MultiPageEditorPart#getAdapter(java.lang.Class)
   */
  @Override
  @SuppressWarnings("unchecked")
  public Object getAdapter(Class required) {

    if (viewAdapter.containsKey(required)) {
      ICEVView view = null;
      if (views.containsKey(required)) {
        view = views.get(required);
      } else {
        ICEVViewFactory viewFactory = viewAdapter.get(required);
        view = viewFactory.createView(this, casDocument, getActivePage());
        if (view != null) {
          views.put(required, view);
        }
      }
      return view;
    }

    if (editorAdapter.containsKey(required)) {
      ICEVEditor editor = null;
      if (editors.containsKey(required)) {
        editor = editors.get(required);
      } else {
        ICEVEditorFactory editorFactory = editorAdapter.get(required);
        editor = editorFactory.createEditor(this, casDocument, getActivePage());
        if (editor != null) {
          editors.put(required, editor);
        }
      }
      return editor;
    }

    return super.getAdapter(required);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.data.ICASAnnotationStateListener#annotationStateChanged
   * (org.apache.uima.cas.Type)
   */
  public void annotationStateChanged(Type type) {
    int activePage = getActivePage();
    int selectionIndex = folderArray[activePage].getSelectionIndex();
    ICEVArtifactViewer viewer = getSelectedViewer(activePage, selectionIndex);
    viewer.annotationStateChanged(type);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.data.ICASAnnotationStateListener#annotationStateChanged
   * (org.apache.uima.cas.text.AnnotationFS)
   */
  public void annotationStateChanged(AnnotationFS annot) {
    int activePage = getActivePage();
    int selectionIndex = folderArray[activePage].getSelectionIndex();
    ICEVArtifactViewer viewer = getSelectedViewer(activePage, selectionIndex);
    viewer.annotationStateChanged();
  }

  private ICEVArtifactViewer getSelectedViewer(int folderIndex, int tabIndex) {
    List<ICEVArtifactViewer> list = casViews.get(folderIndex);
    ICEVArtifactViewer viewer = list.get(tabIndex);
    return viewer;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt .events.MouseEvent)
   */
  public void mouseDoubleClick(MouseEvent e) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events .MouseEvent)
   */
  public void mouseDown(MouseEvent event) {

    // TODO add this functionality again:
    int activePage = getActivePage();
    int selectionIndex = folderArray[activePage].getSelectionIndex();
    ICEVArtifactViewer viewer = getSelectedViewer(activePage, selectionIndex);
    if (event.button == 3) {
      // showPopup();
    } else {
      try {
        int offsetAtLocation = viewer.getOffsetAtLocation(new Point(event.x, event.y));
        if (offsetAtLocation >= 0) {
          showSelection(offsetAtLocation);
        }
      } catch (Exception e) {
      }
    }
    // if (selectionIndex == plainTextIndex[activePage]) {
    // try {
    // showSelection(activeText.getOffsetAtLocation(new Point(event.x, event.y)));
    // } catch (Exception e) {
    // }
    // } else if (event.button == 3)
    // showPopup();
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events. MouseEvent)
   */
  public void mouseUp(MouseEvent event) {
    int activePage = getActivePage();
    int selectionIndex = folderArray[activePage].getSelectionIndex();
    ICEVArtifactViewer viewer = getSelectedViewer(activePage, selectionIndex);
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    boolean selectOnly = store.getBoolean(CEVPreferenceConstants.P_SELECT_ONLY);
    if (event.button == 1 && (selectOnly || (event.stateMask & SWT.ALT) != 0)) {
      Point selection = viewer.getViewerSelectionRange();
      for (ICEVEditor each : editors.values()) {
        each.textSelected(selection.x, selection.x + selection.y);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.data.ICASAnnotationColorListener#colorChanged(org
   * .apache.uima.cas.Type)
   */
  public void colorChanged(Type type) {
    annotationStateChanged(type);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#annotationAdded(org.apache
   * .uima.cas.text.AnnotationFS)
   */
  public void annotationsAdded(List<AnnotationFS> annots) {
    setDirty(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#annotationRemoved(org.apache
   * .uima.cas.text.AnnotationFS)
   */
  public void annotationsRemoved(List<AnnotationFS> annots) {
    setDirty(true);
  }

  public void moveToAnnotation(AnnotationFS annot) {
    int activePage = getActivePage();
    int selectionIndex = folderArray[activePage].getSelectionIndex();
    ICEVArtifactViewer viewer = getSelectedViewer(activePage, selectionIndex);
    viewer.moveToAnnotation(annot);

  }

  public void showSelection(int pos) {
    for (ICEVView each : views.values()) {
      each.newSelection(pos);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse
   * .swt.events.SelectionEvent)
   */
  public void widgetDefaultSelected(SelectionEvent e) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt
   * .events.SelectionEvent)
   */
  public void widgetSelected(SelectionEvent e) {
    int activePage = getActivePage();
    int selectionIndex = folderArray[activePage].getSelectionIndex();
    ICEVArtifactViewer viewer = getSelectedViewer(activePage, selectionIndex);
    viewer.viewerWidgetSelected();
  }

  public void handleViewerPropertyChange(int propertyId) {
    handlePropertyChange(propertyId);
  }

  public List<Type> getInitialVisibleTypes() {
    return initialVisibleTypes;
  }
}
