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

package org.apache.uima.ruta.testing.ui.handlers;

import java.util.Map;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.testing.preferences.TestingPreferenceConstants;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

public class ExtendClasspathHandler implements IHandler, IElementUpdater {

  public static final String COMMAND_ID = "org.apache.uima.ruta.testing.extendClasspath";
  
  @Override
  public void addHandlerListener(IHandlerListener arg0) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();
    boolean b = store.getBoolean(TestingPreferenceConstants.EXTEND_CLASSPATH);
    store.setValue(TestingPreferenceConstants.EXTEND_CLASSPATH, !b);
    ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(
            ICommandService.class);
    service.refreshElements(event.getCommand().getId(), null);
    return null;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isHandled() {
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener arg0) {

  }

  @Override
  public void updateElement(UIElement element, @SuppressWarnings("rawtypes") Map map) {
    IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();
    boolean b = store.getBoolean(TestingPreferenceConstants.EXTEND_CLASSPATH);
    element.setChecked(b);
  }

}
