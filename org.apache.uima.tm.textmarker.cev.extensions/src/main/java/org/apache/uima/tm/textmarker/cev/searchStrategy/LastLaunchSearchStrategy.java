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

package org.apache.uima.tm.textmarker.cev.searchStrategy;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.apache.uima.tm.dltk.internal.core.builder.TextMarkerProjectUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;


public class LastLaunchSearchStrategy implements ICEVSearchStrategy {

  private int priority;

  public LastLaunchSearchStrategy(int priority) {
    super();
    this.priority = priority;
  }

  public int getPriority() {
    return priority;
  }

  public IFile searchDescriptor(IFile file) {
    IProject project = file.getProject();
    ILaunchConfiguration lastRun = DebugUITools
            .getLastLaunch("org.eclipse.debug.ui.launchGroup.run");
    String scriptName = LaunchConfigurationUtils.getString(lastRun,
            ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME, "");
    if (scriptName != null && scriptName.length() != 0 && new Path(scriptName).segmentCount() > 0
            && Path.ROOT.isValidPath(scriptName)) {
      final IFile scriptFile = project.getFile(scriptName);
      if (scriptFile.exists()) {
        IPath path = TextMarkerProjectUtils.getTypeSystemDescriptorPath(scriptFile
                .getProjectRelativePath(), project);
        IFile ts = project.getFile(path.makeRelativeTo(project.getLocation()));
        if (ts.exists()) {
          return ts;
        }
      }
    }
    return null;
  }
}
