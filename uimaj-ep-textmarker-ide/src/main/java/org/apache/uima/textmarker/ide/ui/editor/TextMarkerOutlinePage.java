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

package org.apache.uima.textmarker.ide.ui.editor;

import java.util.ArrayList;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.actions.MemberFilterActionGroup;
import org.eclipse.dltk.ui.viewsupport.MemberFilterAction;
import org.eclipse.dltk.ui.viewsupport.ModelElementFilter;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IActionBars;

public class TextMarkerOutlinePage extends ScriptOutlinePage {

  public TextMarkerOutlinePage(ScriptEditor editor, IPreferenceStore store) {
    super(editor, store);
  }

  @Override
  protected void registerSpecialToolbarActions(IActionBars actionBars) {
    IToolBarManager toolBarManager = actionBars.getToolBarManager();

    MemberFilterActionGroup fMemberFilterActionGroup = new MemberFilterActionGroup(fOutlineViewer,
            fStore);

    String title, helpContext;
    ArrayList actions = new ArrayList(3);

    // fill-in actions variables

    title = ActionMessages.MemberFilterActionGroup_hide_variables_label;
    // TODO help support
    helpContext = "";// IDLTKHelpContextIds.FILTER_FIELDS_ACTION;
    MemberFilterAction hideVariables = new MemberFilterAction(fMemberFilterActionGroup, title,
            new ModelElementFilter(IModelElement.FIELD), helpContext, true);
    hideVariables.setDescription(ActionMessages.MemberFilterActionGroup_hide_variables_description);
    hideVariables.setToolTipText(ActionMessages.MemberFilterActionGroup_hide_variables_tooltip);
    DLTKPluginImages.setLocalImageDescriptors(hideVariables, "filter_fields.gif"); //$NON-NLS-1$
    actions.add(hideVariables);

    // procedures

    title = ActionMessages.MemberFilterActionGroup_hide_functions_label;
    // TODO help support
    helpContext = "";// IDLTKHelpContextIds.FILTER_STATIC_ACTION;
    MemberFilterAction hideProcedures = new MemberFilterAction(fMemberFilterActionGroup, title,
            new ModelElementFilter(IModelElement.METHOD), helpContext, true);
    hideProcedures
            .setDescription(ActionMessages.MemberFilterActionGroup_hide_functions_description);
    hideProcedures.setToolTipText(ActionMessages.MemberFilterActionGroup_hide_functions_tooltip);
    // TODO: add correct icon
    DLTKPluginImages.setLocalImageDescriptors(hideProcedures, "filter_methods.gif"); //$NON-NLS-1$
    actions.add(hideProcedures);

    // namespaces

    title = ActionMessages.MemberFilterActionGroup_hide_classes_label;
    // TODO help support
    helpContext = "";// IDLTKHelpContextIds.FILTER_PUBLIC_ACTION;
    MemberFilterAction hideNamespaces = new MemberFilterAction(fMemberFilterActionGroup, title,
            new ModelElementFilter(IModelElement.TYPE), helpContext, true);
    hideNamespaces.setDescription(ActionMessages.MemberFilterActionGroup_hide_classes_description);
    hideNamespaces.setToolTipText(ActionMessages.MemberFilterActionGroup_hide_classes_tooltip);
    DLTKPluginImages.setLocalImageDescriptors(hideNamespaces, "filter_classes.gif"); //$NON-NLS-1$
    actions.add(hideNamespaces);

    // order corresponds to order in toolbar
    MemberFilterAction[] fFilterActions = (MemberFilterAction[]) actions
            .toArray(new MemberFilterAction[actions.size()]);

    fMemberFilterActionGroup.setActions(fFilterActions);

    fMemberFilterActionGroup.contributeToToolBar(toolBarManager);
  }
}
