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

package org.apache.uima.textmarker.utils.apply;

import org.apache.uima.textmarker.addons.TextMarkerAddonsPlugin;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.FileEditorInput;

public abstract class AbstarctApplyScriptHandler implements IHandler {

  public void addHandlerListener(IHandlerListener handlerListener) {
  }

  public void dispose() {
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbench workbench = TextMarkerAddonsPlugin.getDefault().getWorkbench();
    IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
    IEditorPart part = page.getActiveEditor();
    IEditorInput editorInput = part.getEditorInput();
    if (editorInput instanceof FileEditorInput) {
      FileEditorInput input = (FileEditorInput) editorInput;
      IFile path = input.getFile();
      getJob(event, path).schedule();
    }
    return null;
  }

  abstract AbstractApplyScriptHandlerJob getJob(ExecutionEvent event, IFile path);

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener handlerListener) {

  }
}
