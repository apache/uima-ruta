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

package org.apache.uima.ruta.check;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.caseditor.ide.TypeSystemLocationPropertyPage;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.handlers.HandlerUtil;
import org.xml.sax.SAXException;

public class UpdateTaskHandler implements IHandler {

  private class CheckAnnotationUpdateTaskJob extends Job {

    private String documentSource;

    private String typeSystem;

    private AnnotationCheckComposite composite;

    private String documentSink;

    private AnnotationCheckTreeNode previousSelection;

    CheckAnnotationUpdateTaskJob(String documentSource, String documentSink, String typeSystem,
            AnnotationCheckComposite composite, AnnotationCheckTreeNode previousSelection) {
      super("Update annotation check task...");
      this.documentSource = documentSource;
      this.documentSink = documentSink;
      this.typeSystem = typeSystem;
      this.composite = composite;
      this.previousSelection = previousSelection;
    }

    CheckAnnotationUpdateTaskJob(AnnotationCheckComposite composite,
            AnnotationCheckTreeNode previousSelection) {
      this(composite.getDocumentSource(), composite.getDocumentSink(), composite.getPathToTypeSystem(),
              composite, previousSelection);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
      Map<String, Set<String>> typesToCheck = composite.getCheckedTypes();

      File dir = new File(documentSource);
      File[] listFiles = dir.listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File file, String string) {
          return string.endsWith(".xmi");
        }
      });
      if (listFiles == null) {
        return Status.CANCEL_STATUS;
      }

      File dataFile = new File(documentSink, "data.xml");
      List<CheckDocument> docs = new ArrayList<CheckDocument>();
      try {
        docs = XMLUtils.read(dataFile);
      } catch (SAXException e) {
        RutaAddonsPlugin.error(e);
      } catch (IOException e) {
        RutaAddonsPlugin.error(e);
      }
      setDefaultTypeSystem(documentSource, typeSystem);
      TypeSystemDescription tsd = null;
      try {
        tsd = UIMAFramework.getXMLParser().parseTypeSystemDescription(
                new XMLInputSource(typeSystem));
        tsd.resolveImports();
      } catch (InvalidXMLException e) {
        RutaAddonsPlugin.error(e);
      } catch (IOException e) {
        RutaAddonsPlugin.error(e);
      }
      CAS cas = null;
      try {
        cas = CasCreationUtils.createCas(tsd, null, new FsIndexDescription[0]);
      } catch (ResourceInitializationException e) {
        RutaAddonsPlugin.error(e);
      }

      if (tsd == null || cas == null) {
        return Status.CANCEL_STATUS;
      }
      TreePath treePath = null;
      final IAnnotationCheckTreeNode root = new AnnotationCheckRootNode();
      for (File file : listFiles) {
        cas.reset();
        try {
          XmiCasDeserializer.deserialize(new FileInputStream(file), cas, true);
        } catch (FileNotFoundException e) {
          RutaAddonsPlugin.error(e);
        } catch (SAXException e) {
          RutaAddonsPlugin.error(e);
        } catch (IOException e) {
          RutaAddonsPlugin.error(e);
        }
        CheckDocument element = getCheckDocument(docs, file.getAbsolutePath());
        boolean documentAlreadyDone = documentAlreadyDoneforTypes(element, new LinkedList<String>(
                typesToCheck.keySet()));
        if (!documentAlreadyDone) {
          AnnotationCheckTreeNode node = new AnnotationCheckTreeNode(root, element);
          root.addChild(node);
          AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex();
          for (AnnotationFS each : annotationIndex) {
            boolean annotationAlreadyDoneforTypes = annotationAlreadyDoneforTypes(each,
                    typesToCheck.keySet(), element.checkedTypes);
            if (!annotationAlreadyDoneforTypes) {
              CheckElement ac = new CheckAnnotation(each);
              AnnotationCheckTreeNode anode = new AnnotationCheckTreeNode(node, ac);
              for (String feature : typesToCheck.get(each.getType().getName())) {
                anode.addChild(new FeatureCheckTreeNode(anode, each.getType().getFeatureByBaseName(
                        feature), each.getFeatureValueAsString(each.getType().getFeatureByBaseName(
                        feature))));
              }
              if (treePath == null) {
                treePath = new TreePath(new Object[] { root, node, anode });
              }
              node.addChild(anode);
            }
          }
          if (treePath == null) {
            treePath = new TreePath(new Object[] { root, node });
          }
        }
      }
      if (root.getChildren().length == 0) {
        return Status.OK_STATUS;
      }
      if (previousSelection != null) {
        CheckElement element = previousSelection.getElement();
        TreePath oldPath = getPathTo(element, root);
        if (oldPath != null) {
          treePath = oldPath;
        }
      }
      final TreeSelection firstSelection = new TreeSelection(treePath);

      cas.release();
      composite.setOldDocs(docs);
      composite.getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          TreeViewer treeView = composite.getTreeViewer();
          treeView.setInput(root);
          treeView.setSelection(firstSelection, true);
          treeView.expandToLevel(
                  ((AnnotationCheckTreeNode) firstSelection.getFirstElement()).getParent(),
                  TreeViewer.ALL_LEVELS);
        }
      });

      return Status.OK_STATUS;
    }

    private TreePath getPathTo(CheckElement element, IAnnotationCheckTreeNode root) {
      AnnotationCheckTreeNode[] children = root.getChildren();
      for (AnnotationCheckTreeNode eachDocNode : children) {
        if (element instanceof CheckAnnotation) {
          AnnotationCheckTreeNode[] children2 = eachDocNode.getChildren();
          for (AnnotationCheckTreeNode eachANode : children2) {
            if (isSameElement(eachANode.getElement(), element)) {
              return new TreePath(new Object[] { root, eachDocNode, eachANode });
            }
          }
        } else {
          if (isSameElement(eachDocNode.getElement(), element)) {
            return new TreePath(new Object[] { root, eachDocNode });
          }
        }

      }
      return null;
    }

    private boolean isSameElement(CheckElement e1, CheckElement e2) {
      if (e1 == null || e2 == null) {
        return false;
      }
      if (e1 instanceof CheckAnnotation && e2 instanceof CheckAnnotation) {
        CheckAnnotation ca1 = (CheckAnnotation) e1;
        CheckAnnotation ca2 = (CheckAnnotation) e2;
        return ca1.getBegin() == ca2.getBegin() && ca1.getEnd() == ca2.getEnd()
                && ca1.getTypeName().equals(ca2.getTypeName());
      }
      if (e1 instanceof CheckDocument && e2 instanceof CheckDocument) {
        CheckDocument cd1 = (CheckDocument) e1;
        CheckDocument cd2 = (CheckDocument) e2;
        return cd1.source.equals(cd2.source);
      }
      return false;
    }

    private boolean documentAlreadyDoneforTypes(CheckDocument element, List<String> typesToCheck) {
      if (element.checkedTypes.isEmpty() && !typesToCheck.isEmpty()) {
        return false;
      }

      boolean allCovered = true;
      for (String each : typesToCheck) {
        allCovered &= element.checkedTypes.contains(each);
      }
      return allCovered;
    }

    private boolean annotationAlreadyDoneforTypes(AnnotationFS each,
            Collection<String> typesToCheck, Collection<String> alreadyDone) {
      String name = each.getType().getName();
      if (alreadyDone.contains(name)) {
        return true;
      }
      return !typesToCheck.contains(name);
    }

    private CheckDocument getCheckDocument(List<CheckDocument> docs, String absolutePath) {
      for (CheckDocument checkDocument : docs) {
        if (checkDocument.source.equals(absolutePath)) {
          return checkDocument;
        }
      }
      return new CheckDocument(absolutePath);
    }

    public void setDefaultTypeSystem(String documentSource, String typeSystemLocation) {
      IFile dsFile = CheckAnnotationUtils.getIFile(documentSource);
      IFile tsFile = CheckAnnotationUtils.getIFile(typeSystemLocation);
      IProject project = dsFile.getProject();
      String portableString = tsFile.getFullPath().toPortableString();
      TypeSystemLocationPropertyPage.setTypeSystemLocation(project, portableString);
    }

  }

  @Override
  public void addHandlerListener(IHandlerListener arg0) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    AnnotationCheckView acView;
    try {
      acView = (AnnotationCheckView) HandlerUtil.getActiveWorkbenchWindow(event).getWorkbench()
              .getActiveWorkbenchWindow().getActivePage().showView(AnnotationCheckView.ID);
      AnnotationCheckComposite composite = acView.getComposite();
      TreeSelection selection = (TreeSelection) composite.getTreeViewer().getSelection();
      AnnotationCheckTreeNode previousSelection = (AnnotationCheckTreeNode) selection
              .getFirstElement();
      CheckAnnotationUpdateTaskJob job = new CheckAnnotationUpdateTaskJob(composite,
              previousSelection);
      job.schedule();
    } catch (Exception e) {
      RutaAddonsPlugin.error(e);
      return Status.CANCEL_STATUS;
    }
    return Status.OK_STATUS;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isHandled() {
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener arg0) {

  }

}
