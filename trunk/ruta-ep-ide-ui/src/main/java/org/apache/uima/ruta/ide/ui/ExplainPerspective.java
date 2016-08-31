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

package org.apache.uima.ruta.ide.ui;

import org.apache.uima.ruta.ide.ui.wizards.RutaFileCreationWizard;
import org.apache.uima.ruta.ide.ui.wizards.RutaPackageCreationWizard;
import org.apache.uima.ruta.ide.ui.wizards.RutaProjectCreationWizard;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;

public class ExplainPerspective implements IPerspectiveFactory {

  public static final String FAILED_RULES = "org.apache.uima.ruta.explain.failed";

  public static final String MATCHED_RULES = "org.apache.uima.ruta.explain.matched";

  public static final String RULE_ELEMENTS = "org.apache.uima.ruta.explain.element";

  public static final String APPLIED_RULES = "org.apache.uima.ruta.explain.apply";

  public static final String SELECTION_RULES = "org.apache.uima.ruta.explain.selection";

  public static final String RULE_LIST = "org.apache.uima.ruta.explain.rulelist";

  public static final String CREATED_BY = "org.apache.uima.ruta.explain.createdBy";

  public static final String TYPE_BROWSER = "org.apache.uima.caseditor.browser";

  public static final String SELECTION_VIEW = "org.apache.uima.caseditor.selection";

  public static final String QUERY = "org.apache.uima.ruta.query.ui.ScriptQueryView";

  public static final String SCRIPT_EXPLORER = "org.eclipse.dltk.ui.ScriptExplorer";

  public static final String NEW_FOLDER_WIZARD = "org.eclipse.ui.wizards.new.folder"; //$NON-NLS-1$ 

  public static final String NEW_FILE_WIZARD = "org.eclipse.ui.wizards.new.file"; //$NON-NLS-1$

  public static final String NEW_UNTITLED_TEXT_FILE_WIZARD = "org.eclipse.ui.editors.wizards.UntitledTextFileWizard"; //$NON-NLS-1$

  public static final String ID_NEW_SOURCE_WIZARD = "org.apache.uima.ruta.ide.ui.wizards.NewSourceFolderCreationWizard";

  public static final String ID_NEW_PACKAGE_WIZARD = "org.apache.uima.ruta.ide.ui.wizards.NewPackageCreationWizard";

  protected void addNewWizardShortcuts(IPageLayout layout) {
    layout.addNewWizardShortcut(RutaProjectCreationWizard.ID_WIZARD);
    layout.addNewWizardShortcut(RutaFileCreationWizard.ID_WIZARD);

    layout.addNewWizardShortcut(ID_NEW_SOURCE_WIZARD);
    layout.addNewWizardShortcut(RutaPackageCreationWizard.ID_WIZARD);

    layout.addNewWizardShortcut(NEW_FOLDER_WIZARD);
    layout.addNewWizardShortcut(NEW_FILE_WIZARD);
    layout.addNewWizardShortcut(NEW_UNTITLED_TEXT_FILE_WIZARD);
  }

  protected void addShowViewShortcuts(IPageLayout layout) {
    layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
    layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
    layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);

    layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
    layout.addShowViewShortcut(IProgressConstants.PROGRESS_VIEW_ID);

    layout.addShowViewShortcut(SCRIPT_EXPLORER);
    layout.addShowViewShortcut(TYPE_BROWSER);
    layout.addShowViewShortcut(SELECTION_VIEW);
    layout.addShowViewShortcut(CREATED_BY);
    layout.addShowViewShortcut(QUERY);

  }

  public void createFolders(IPageLayout layout) {
    final String editorArea = layout.getEditorArea();

    IFolderLayout rightFolder = layout.createFolder("rightFolder", IPageLayout.RIGHT, (float) 0.75,
            editorArea);
    rightFolder.addView(APPLIED_RULES);
    rightFolder.addView(SELECTION_RULES);
    rightFolder.addView(RULE_LIST);
    rightFolder.addView(TYPE_BROWSER);
    rightFolder.addView(SELECTION_VIEW);

    IFolderLayout ruleFolder = layout.createFolder("ruleFolder", IPageLayout.BOTTOM, (float) 0.6,
            "rightFolder");
    ruleFolder.addView(RULE_ELEMENTS);
    ruleFolder.addView(CREATED_BY);

    IFolderLayout navigationFolder = layout.createFolder("left", IPageLayout.LEFT, (float) 0.2,
            editorArea);
    navigationFolder.addView(SCRIPT_EXPLORER);

    IFolderLayout matchedFolder = layout.createFolder("matchedFolder", IPageLayout.BOTTOM,
            (float) 0.75, editorArea);
    matchedFolder.addView(MATCHED_RULES);

    IFolderLayout failedFolder = layout.createFolder("failedFolder", IPageLayout.BOTTOM,
            (float) 0.75, editorArea);
    failedFolder.addView(FAILED_RULES);

  }

  protected void addPerspectiveShotcuts(IPageLayout layout) {
    layout.addPerspectiveShortcut("org.apache.uima.ruta.ide.ui.RutaPerspective");
  }

  public void createInitialLayout(IPageLayout layout) {
    createFolders(layout);
    addShowViewShortcuts(layout);
    addNewWizardShortcuts(layout);
    addPerspectiveShotcuts(layout);

  }

}
