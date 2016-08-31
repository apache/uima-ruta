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

package org.apache.uima.ruta.searchStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.uima.caseditor.ide.TypeSystemLocationPropertyPage;
import org.apache.uima.caseditor.ide.searchstrategy.ITypeSystemSearchStrategy;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
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

  public IFile findTypeSystem(IFile casFile) {
    IProject project = casFile.getProject();

    IFile typeSystemLocation = TypeSystemLocationPropertyPage.getTypeSystemLocation(project);
    if (typeSystemLocation != null && !typeSystemLocation.getName().equals("TypeSystem.xml")) {
      // do not override the properties!
      return null;
    }

    try {
      IProjectNature nature = project.getNature(RutaNature.NATURE_ID);
      if (!(nature instanceof RutaNature)) {
        return null;
      }
    } catch (CoreException e) {
      return null;
    }

    try {
      Set<IFile> set = new TreeSet<>();
      List<IFolder> descriptorFolders = RutaProjectUtils.getDescriptorFolders(project);
      for (IFolder folder : descriptorFolders) {
        set.addAll(collectTypeSystems(folder));

      }

      ListDialog ld = new ListDialog(Display.getCurrent().getActiveShell());
      ld.setContentProvider(new IStructuredContentProvider() {

        public void dispose() {

        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

        }

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
      if (set == null || set.isEmpty()) {
        return null;
      }

      ld.setTitle("Select Type System Descriptor");
      ld.setInput(set);
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
