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

import java.util.HashMap;
import java.util.List;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

public class SetAnnotationModeContributionFactory extends ExtensionContributionFactory {

  public SetAnnotationModeContributionFactory() {
    super();
  }

  @Override
  public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
    AnnotationCheckView acView;
    try {
      acView = (AnnotationCheckView) Workbench.getInstance().getActiveWorkbenchWindow()
              .getActivePage().showView(AnnotationCheckView.ID);
      AnnotationCheckComposite composite = (AnnotationCheckComposite) acView.getComposite();
      List<String> selectedTypes = composite.getSelectedTypes();
      CommandContributionItemParameter pd = new CommandContributionItemParameter(serviceLocator,
              "", SetAnnotationModeHandler.MODE, SWT.PUSH);
      HashMap<String, String> mapd = new HashMap<String, String>();
      pd.label = "uima.tcas.Annotation";
      mapd.put(SetAnnotationModeHandler.TYPE, pd.label);
      pd.parameters =  mapd;
      pd.icon = RutaAddonsPlugin.getImageDescriptor("icons/font_add.png");
      CommandContributionItem itemp = new CommandContributionItem(pd);
      itemp.setVisible(true);
      additions.addContributionItem(itemp, null);
      for (String each : selectedTypes) {
        CommandContributionItemParameter p = new CommandContributionItemParameter(serviceLocator,
                "", SetAnnotationModeHandler.MODE, SWT.PUSH);
        p.label = each;
        p.icon = RutaAddonsPlugin.getImageDescriptor("icons/font_add.png");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(SetAnnotationModeHandler.TYPE, p.label);
        p.parameters =  map;
        CommandContributionItem item = new CommandContributionItem(p);
        item.setVisible(true);
        additions.addContributionItem(item, null);
      }
    } catch (PartInitException e) {
      RutaAddonsPlugin.error(e);
    }
  }

}
