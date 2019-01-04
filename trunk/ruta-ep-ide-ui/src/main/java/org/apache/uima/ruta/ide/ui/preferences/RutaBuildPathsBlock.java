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

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class RutaBuildPathsBlock extends BuildpathsBlock {
  public RutaBuildPathsBlock(IRunnableContext runnableContext, IStatusChangeListener context,
          int pageToShow, boolean useNewPage, IWorkbenchPreferenceContainer pageContainer) {
    super(runnableContext, context, pageToShow, useNewPage, pageContainer);
  }

  @Override
  protected IPreferenceStore getPreferenceStore() {
    return RutaIdeUIPlugin.getDefault().getPreferenceStore();
  }

  @Override
  protected boolean supportZips() {
    return true;
  }
}
