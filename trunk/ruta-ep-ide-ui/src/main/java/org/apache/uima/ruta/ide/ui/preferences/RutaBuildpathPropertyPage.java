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

package org.apache.uima.ruta.ide.ui.preferences;

import org.apache.uima.ruta.ide.core.RutaLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class RutaBuildpathPropertyPage extends BuildPathsPropertyPage implements
        IWorkbenchPropertyPage {
  public RutaBuildpathPropertyPage() {
  }

  @Override
  protected BuildpathsBlock createBuildPathBlock(IWorkbenchPreferenceContainer pageContainer) {
    return new RutaBuildPathsBlock(new BusyIndicatorRunnableContext(), this, getSettings().getInt(
            INDEX), false, pageContainer);
  }

  public IDLTKLanguageToolkit getLanguageToolkit() {
    return RutaLanguageToolkit.getDefault();
  }

}
