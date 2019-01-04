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

package org.apache.uima.ruta.cde.ui;

import org.apache.uima.ruta.ide.ui.wizards.RutaFileCreationWizard;
import org.apache.uima.ruta.ide.ui.wizards.RutaPackageCreationWizard;
import org.apache.uima.ruta.ide.ui.wizards.RutaProjectCreationWizard;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;

public class CDEPerspectiveFactory implements IPerspectiveFactory {

  public static final String TYPE_BROWSER = "org.apache.uima.caseditor.browser";

  public static final String SELECTION_VIEW = "org.apache.uima.caseditor.selection";

  public static final String ANNOTATION_TESTING = "org.apache.uima.ruta.testing.TestingView";

  public static final String QUERY = "org.apache.uima.ruta.query.ui.ScriptQueryView";

  public static final String TEXTRULER = "org.apache.uima.ruta.ml.MainView";

  public static final String SCRIPT_EXPLORER = "org.eclipse.dltk.ui.ScriptExplorer";

  public static final String NEW_FOLDER_WIZARD = "org.eclipse.ui.wizards.new.folder"; //$NON-NLS-1$ 

  public static final String NEW_FILE_WIZARD = "org.eclipse.ui.wizards.new.file"; //$NON-NLS-1$

  public static final String NEW_UNTITLED_TEXT_FILE_WIZARD = "org.eclipse.ui.editors.wizards.UntitledTextFileWizard"; //$NON-NLS-1$

  public static final String ID_NEW_SOURCE_WIZARD = "org.apache.uima.ruta.ide.ui.wizards.NewSourceFolderCreationWizard";

  public static final String ID_NEW_PACKAGE_WIZARD = "org.apache.uima.ruta.ide.ui.wizards.NewPackageCreationWizard";

  public static final String CONSTRAINTSELECT = "org.apache.uima.ruta.cde.ui.ConstraintSelectView";

  public static final String DOCUMENTSELECT = "org.apache.uima.ruta.cde.ui.DocumentView";

  public static final String RESULTVIEW = "org.apache.uima.ruta.cde.ui.ResultView";

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
    layout.addShowViewShortcut(TEXTRULER);
    layout.addShowViewShortcut(ANNOTATION_TESTING);
    layout.addShowViewShortcut(QUERY);
    layout.addShowViewShortcut(DOCUMENTSELECT);
    layout.addShowViewShortcut(CONSTRAINTSELECT);
    layout.addShowViewShortcut(RESULTVIEW);

  }

  protected void addActionSets(IPageLayout layout) {

  }

  public void createFolders(IPageLayout layout) {
    final String editorArea = layout.getEditorArea();

    IFolderLayout rightFolder = layout.createFolder("right", IPageLayout.RIGHT, (float) 0.8,
            editorArea);
    rightFolder.addView(DOCUMENTSELECT);
    rightFolder.addView(IPageLayout.ID_OUTLINE);
    rightFolder.addView(TYPE_BROWSER);

    // Folder
    IFolderLayout folder = layout.createFolder("left", IPageLayout.LEFT, (float) 0.2, editorArea); //$NON-NLS-1$		

    folder.addView(SCRIPT_EXPLORER);
    folder.addPlaceholder(IPageLayout.ID_BOOKMARKS);

    // Output folder
    IFolderLayout outputFolder = layout.createFolder(
            "bottom", IPageLayout.BOTTOM, (float) 0.68, editorArea); //$NON-NLS-1$

    outputFolder.addView(CONSTRAINTSELECT);
    outputFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
    outputFolder.addView(IPageLayout.ID_TASK_LIST);
    outputFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);
    outputFolder.addView(SELECTION_VIEW);
    outputFolder.addView(TEXTRULER);
    outputFolder.addView(ANNOTATION_TESTING);
    outputFolder.addView(QUERY);

    IFolderLayout resultFolder = layout.createFolder("bottom-right", IPageLayout.BOTTOM,
            (float) 0.6, "right");
    resultFolder.addView(RESULTVIEW);

    outputFolder.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
    outputFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);
    outputFolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
  }

  protected void addPerspectiveShotcuts(IPageLayout layout) {
    layout.addPerspectiveShortcut("org.apache.uima.ruta.ide.ui.explainPerspective");
  }

  public void createInitialLayout(IPageLayout layout) {
    createFolders(layout);
    addActionSets(layout);
    addShowViewShortcuts(layout);
    addNewWizardShortcuts(layout);
    addPerspectiveShotcuts(layout);
  }
}
