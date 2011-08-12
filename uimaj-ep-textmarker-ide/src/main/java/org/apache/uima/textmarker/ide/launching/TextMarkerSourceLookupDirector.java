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

package org.apache.uima.textmarker.ide.launching;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IPersistableSourceLocator;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.dltk.internal.debug.core.model.ScriptStackFrame;

public class TextMarkerSourceLookupDirector implements IPersistableSourceLocator {

  public TextMarkerSourceLookupDirector() {
  }

  public Object getSourceElement(IStackFrame stackFrame) {
    if (stackFrame instanceof ScriptStackFrame) {
      ScriptStackFrame sf = (ScriptStackFrame) stackFrame;
      URI uri = sf.getFileName();

      String pathname = uri.getPath();
      if (Platform.getOS().equals(Platform.OS_WIN32)) {
        pathname = pathname.substring(1);
      }

      File file = new File(pathname);

      IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
      IContainer container = root.getContainerForLocation(new Path(file.getParent()));

      if (container != null) {
        IResource resource = container.findMember(file.getName());

        if (resource instanceof IFile) {
          return resource;
        }
      } else {
        return file;
      }
    }

    return null;
  }

  public String getMemento() throws CoreException {
    return null;
  }

  public void initializeDefaults(ILaunchConfiguration configuration) throws CoreException {

  }

  public void initializeFromMemento(String memento) throws CoreException {

  }
}
