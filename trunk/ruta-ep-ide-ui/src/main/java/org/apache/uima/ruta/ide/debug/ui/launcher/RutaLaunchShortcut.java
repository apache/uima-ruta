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

package org.apache.uima.ruta.ide.debug.ui.launcher;

import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.launching.RutaLaunchConfigurationConstants;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.dltk.internal.debug.ui.launcher.AbstractScriptLaunchShortcut;

public class RutaLaunchShortcut extends AbstractScriptLaunchShortcut {
  @Override
  protected ILaunchConfigurationType getConfigurationType() {
    return getLaunchManager().getLaunchConfigurationType(
            RutaLaunchConfigurationConstants.ID_RUTA_SCRIPT);
  }

  @Override
  protected String getNatureId() {
    return RutaNature.NATURE_ID;
  }
}
