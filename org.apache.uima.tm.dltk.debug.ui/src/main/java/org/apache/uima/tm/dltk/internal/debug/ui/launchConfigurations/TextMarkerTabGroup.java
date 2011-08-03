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

package org.apache.uima.tm.dltk.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptCommonTab;

import org.apache.uima.tm.dltk.internal.debug.ui.interpreters.TextMarkerInterpreterTab;

public class TextMarkerTabGroup extends AbstractLaunchConfigurationTabGroup {
  public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
    TextMarkerMainLaunchConfigurationTab main = new TextMarkerMainLaunchConfigurationTab(mode);
    ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { main,
        new TextMarkerArgumentsTab(), new TextMarkerInterpreterTab(main), new EnvironmentTab(),
        new ScriptCommonTab(main) };
    setTabs(tabs);
  }
}
