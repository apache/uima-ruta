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

package org.apache.uima.textmarker.textruler.ui;

import java.util.List;

import org.apache.uima.textmarker.textruler.TextRulerPlugin;
import org.apache.uima.textmarker.textruler.extension.TextRulerController;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerController;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerParameter;
import org.apache.uima.textmarker.textruler.preferences.AlgorithmPreferencePage;
import org.apache.uima.textmarker.textruler.preferences.ConfigPreferencePage;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class ConfigureLearnersHandler implements IHandler {
  public void addHandlerListener(IHandlerListener handlerListener) {

  }

  public void dispose() {

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
    PreferenceManager pm = new PreferenceManager();

    PreferencePage page = new ConfigPreferencePage();
    page.setTitle("TextRuler");
    PreferenceNode node = new PreferenceNode("org.apache.uima.textmarker.textruler.config", page);
    pm.addToRoot(node);

    List<PreferenceNode> nodes = pm.getElements(0);
    PreferenceNode top = null;
    for (PreferenceNode n : nodes)
      if (n.getId().equals("org.apache.uima.textmarker.textruler.config"))
        top = n;
    if (top != null) {
      for (TextRulerLearnerController ctrl : TextRulerController.getAvailableControllers()) {
        TextRulerLearnerParameter[] params = ctrl.getFactory().getAlgorithmParameters();
        if (params == null || params.length == 0)
          continue;
        page = new AlgorithmPreferencePage(ctrl);
        page.setTitle(ctrl.getName());
        node = new PreferenceNode(ctrl.getID(), page);
        top.add(node);
      }
    }

    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    PreferenceDialog pd = new PreferenceDialog(shell, pm);
    pd.setPreferenceStore(TextRulerPlugin.getDefault().getPreferenceStore());
    pd.create();
    pd.open();

    // Dialog dialog = PreferencesUtil.createPreferenceDialogOn(HandlerUtil.getActiveShell(event),
    // TestingPreferencePage.ID, new String[] { TestingPreferencePage.ID }, null);
    // dialog.open();
    return null;
  }

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener handlerListener) {

  }

}
