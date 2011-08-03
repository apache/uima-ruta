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

package org.apache.uima.tm.textmarker.testing.searchStrategy;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.apache.uima.tm.dltk.internal.core.builder.TextMarkerProjectUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;


public class TestingSearchStrategy implements ICEVSearchStrategy {

  private int priority;

  public TestingSearchStrategy(int priority) {
    super();
    this.priority = priority;
  }

  @Override
  public int getPriority() {
    return priority;
  }

  @Override
  public IFile searchDescriptor(IFile file) {
    IPath location = file.getLocation();
    IProject project = file.getProject();
    IFolder testFolder = project.getFolder(TextMarkerProjectUtils.getDefaultTestLocation());
    IFolder descFolder = project.getFolder(TextMarkerProjectUtils.getDefaultDescriptorLocation());
    IPath relativeTo = location.makeRelativeTo(testFolder.getLocation());
    IPath segments = relativeTo.removeLastSegments(2);
    String scriptName = segments.lastSegment();
    scriptName += "TypeSystem.xml";
    segments = segments.removeLastSegments(1);
    IFolder descPackageFolder = null;
    try {
      descPackageFolder = descFolder.getFolder(segments);
    } catch (Exception e) {
      return null;
    }
    if (descPackageFolder == null || !descPackageFolder.exists()) {
      return null;
    }
    IFile result = descPackageFolder.getFile(scriptName);
    if (result == null || !result.exists()) {
      return null;
    }
    return result;
  }

}
