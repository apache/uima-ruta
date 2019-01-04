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

package org.apache.uima.ruta.check;

import java.util.Map;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

public class SetAnnotationModeHandler implements IHandler, IElementUpdater {

  public static final String TYPE = SetAnnotationModeHandler.MODE + ".type";

  public static final String MODE = "org.apache.uima.ruta.check.mode";

  private String mode = "Annotation";

  public void addHandlerListener(IHandlerListener arg0) {

  }

  public void dispose() {

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
    String typeString = event.getParameter(TYPE);
    if (typeString != null) {

      AnnotationCheckView acView;
      try {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        acView = (AnnotationCheckView) window.getActivePage().showView(AnnotationCheckView.ID);

        AnnotationCheckComposite composite = (AnnotationCheckComposite) acView.getComposite();
        composite.setAnnotationMode(typeString);
        String[] split = typeString.split("[.]");
        mode = split[split.length - 1];
        ICommandService commandService = (ICommandService) window.getService(ICommandService.class);
        if (commandService != null) {
          commandService.refreshElements("org.apache.uima.ruta.check.mode", null);
        }
      } catch (PartInitException e) {
        RutaAddonsPlugin.error(e);
        return Status.CANCEL_STATUS;
      }
    }
    return Status.OK_STATUS;
  }

  public void updateElement(UIElement element, @SuppressWarnings("rawtypes")
  Map parameters) {
    if (parameters.get(TYPE) == null) {
      element.setText(mode);
    }
  }

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener arg0) {

  }

}
