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

package org.apache.uima.textmarker.ide.ui.wizards;

import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;


public class TextMarkerFileCreationPage extends NewSourceModulePage {

  @Override
  protected String getPageDescription() {
    return "This wizard creates a new TextMarker script file.";
  }

  @Override
  protected String getFileContent() {
    StringBuilder sb = new StringBuilder();
    sb.append("PACKAGE ");
    IScriptFolder scriptFolder = getScriptFolder();
    IModelElement ancestor = scriptFolder.getAncestor(IModelElement.PROJECT_FRAGMENT);
    IPath path = ancestor.getPath().removeFirstSegments(1);
    String pathString = "";
    for (int i = 1; i < path.segments().length; i++) {
      pathString += path.segments()[i];
      if (i < path.segments().length - 1) {
        pathString += ".";
      }
    }
    sb.append(pathString);
    sb.append(";\n");
    return sb.toString();
  }

  @Override
  protected String getRequiredNature() {
    return TextMarkerNature.NATURE_ID;
  }

  @Override
  protected String getPageTitle() {
    return "Create a new TextMarker script file";
  }
}
