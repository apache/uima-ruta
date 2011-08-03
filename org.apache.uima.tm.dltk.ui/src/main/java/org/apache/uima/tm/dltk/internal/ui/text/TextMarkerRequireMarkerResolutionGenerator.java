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

package org.apache.uima.tm.dltk.internal.ui.text;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.CorrectionEngine;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

public class TextMarkerRequireMarkerResolutionGenerator implements IMarkerResolutionGenerator {

  public IMarkerResolution[] getResolutions(IMarker marker) {
    if (TextMarkerCorrectionProcessor.isFixable(marker)) {
      String pkgName = CorrectionEngine.getProblemArguments(marker)[0];
      if (pkgName != null) {
        IProject project = marker.getResource().getProject();
        IScriptProject scriptProject = DLTKCore.create(project);
        return new IMarkerResolution[] { new TextMarkerRequirePackageMarkerResolution(pkgName,
                scriptProject) };
      }
    }
    return new IMarkerResolution[0];
  }
}
