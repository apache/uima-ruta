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

package org.apache.uima.textmarker.searchStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.caseditor.ide.searchstrategy.ITypeSystemSearchStrategy;
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ListDialog;

public class DescriptorFolderSearchStrategy implements ITypeSystemSearchStrategy {

  private List<IFile> collectTypeSystems(IFolder folder) throws CoreException {
    List<IFile> result = new ArrayList<IFile>();
    for (IResource each : folder.members()) {
      if (each instanceof IFolder) {
        result.addAll(collectTypeSystems((IFolder) each));
      } else if (each instanceof IFile && each.getFileExtension() != null
              && each.getFileExtension().equals("xml")) {
        result.add((IFile) each);
      }
    }
    return result;
  }

  @Override
  public IFile findTypeSystem(IFile casFile) {
    IProject project = casFile.getProject();

    try {
      IProjectNature nature = project.getNature(TextMarkerNature.NATURE_ID);
      if (!(nature instanceof TextMarkerNature)) {
        return null;
      }
    } catch (CoreException e) {
      return null;
    }

    IFolder folder = project.getFolder(TextMarkerProjectUtils.getDefaultDescriptorLocation());
    try {
      List<IFile> list = collectTypeSystems(folder);
      ListDialog ld = new ListDialog(Display.getCurrent().getActiveShell());
      ld.setContentProvider(new IStructuredContentProvider() {

        @Override
        public void dispose() {

        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

        }

        @Override
        public Object[] getElements(Object inputElement) {
          return ((Collection<?>) inputElement).toArray();
        }

      });
      ld.setLabelProvider(new LabelProvider() {
        @Override
        public String getText(Object element) {
          return ((IFile) element).getName();
        }
      });
      if (list == null || list.isEmpty()) {
        return null;
      }

      ld.setTitle("Select Type System Descriptor");
      ld.setInput(list);
      ld.open();

      if (ld.getResult() != null) {
        return (IFile) ld.getResult()[0];
      } else {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
  }
}
