package org.apache.uima.tm.textmarker.testing.ui.views;

import org.apache.uima.tm.dltk.internal.ui.wizards.*;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;


//import org.apache.uima.tm.dltk.internal.ui.wizards.TextMarkerFileCreationWizard;
//import org.apache.uima.tm.dltk.internal.ui.wizards.TextMarkerPackageCreationWizard;
//import org.apache.uima.tm.dltk.internal.ui.wizards.TextMarkerProjectCreationWizard;

public class TestingPerspective implements IPerspectiveFactory {

  public static final String BASIC_STREAM = "org.apache.uima.tm.textmarker.cev.debug.basic";

  public static final String PALETTE_VIEW = "org.apache.uima.tm.cev.views.palette";

  public static final String TYPE_BROWSER = "org.apache.uima.tm.cev.views.annotationBrowser";

  public static final String SELECTION_VIEW = "org.apache.uima.tm.cev.views.selection";

  public static final String ANNOTATION_EDITOR = "org.apache.uima.tm.cev.views.editor";

  public static final String SCRIPT_EXPLORER = "org.eclipse.dltk.ui.ScriptExplorer";

  public static final String NEW_FOLDER_WIZARD = "org.eclipse.ui.wizards.new.folder"; //$NON-NLS-1$ 

  public static final String NEW_FILE_WIZARD = "org.eclipse.ui.wizards.new.file"; //$NON-NLS-1$

  public static final String NEW_UNTITLED_TEXT_FILE_WIZARD = "org.eclipse.ui.editors.wizards.UntitledTextFileWizard"; //$NON-NLS-1$

  public static final String ID_NEW_SOURCE_WIZARD = "org.apache.uima.tm.dltk.ui.wizards.NewSourceFolderCreationWizard";

  public static final String ID_NEW_PACKAGE_WIZARD = "org.apache.uima.tm.dltk.ui.wizards.NewPackageCreationWizard";

  public static final String ID_PROJECT_CREATION_WIZARD ="org.apache.uima.tm.dltk.internal.ui.wizards.TextMarkerProjectWizard";
  
  public static final String ID_FILE_CREATION_WIZARD = "org.apache.uima.tm.dltk.internal.ui.wizards.TextMarkerFileCreationWizard";
  
  public static final String ID_PACKAGE_CREATION_WIZARD ="org.apache.uima.tm.dltk.ui.wizards.NewPackageCreationWizard";
  
  public static final String ID_TESTING_VIEW ="org.apache.uima.tm.textmarker.ui.testing.TestingView";
  
  public static final String ID_TRUEPOSITIVE_VIEW = "org.apache.uima.tm.textmarker.testing.truePositive";
  
  public static final String ID_FALSEPOSITIVEVIEW = "org.apache.uima.tm.textmarker.testing.falsePositive";
    
  public static final String ID_FALSENEGATIVEVIEW = "org.apache.uima.tm.textmarker.testing.falseNegative";
  
  protected void addNewWizardShortcuts(IPageLayout layout) {
    
    layout.addNewWizardShortcut(ID_PROJECT_CREATION_WIZARD);
    layout.addNewWizardShortcut(ID_FILE_CREATION_WIZARD);

    layout.addNewWizardShortcut(ID_NEW_SOURCE_WIZARD);
    layout.addNewWizardShortcut(ID_PACKAGE_CREATION_WIZARD);

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
    layout.addShowViewShortcut(BASIC_STREAM);
    layout.addShowViewShortcut(PALETTE_VIEW);
    layout.addShowViewShortcut(TYPE_BROWSER);
    layout.addShowViewShortcut(SELECTION_VIEW);
    layout.addShowViewShortcut(ANNOTATION_EDITOR);

  }

  protected void addActionSets(IPageLayout layout) {

  }

  protected void createFolders(IPageLayout layout) {
    final String editorArea = layout.getEditorArea();

    IFolderLayout rightFolder = layout.createFolder("right", IPageLayout.RIGHT, (float) 0.75,
            editorArea);
    rightFolder.addView(IPageLayout.ID_OUTLINE);
    rightFolder.addView(TYPE_BROWSER);
    rightFolder.addView(PALETTE_VIEW);
    rightFolder.addView("BASIC_STREAM");
    rightFolder.addView(ID_TRUEPOSITIVE_VIEW);
    rightFolder.addView(ID_FALSEPOSITIVEVIEW);
    rightFolder.addView(ID_FALSENEGATIVEVIEW);
    // Folder
    IFolderLayout folder = layout.createFolder("left", IPageLayout.LEFT, (float) 0.2, editorArea); //$NON-NLS-1$    

    folder.addView(SCRIPT_EXPLORER);
    folder.addPlaceholder(IPageLayout.ID_BOOKMARKS);

    // Output folder
    IFolderLayout outputFolder = layout.createFolder(
            "bottom", IPageLayout.BOTTOM, (float) 0.75, editorArea); //$NON-NLS-1$

    outputFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
    outputFolder.addView(IPageLayout.ID_TASK_LIST);
    outputFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);
    outputFolder.addView(SELECTION_VIEW);
    outputFolder.addView(ANNOTATION_EDITOR);
    outputFolder.addView(ID_TESTING_VIEW);


    outputFolder.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
    outputFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);
    outputFolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
  }

  protected void addPerspectiveShotcuts(IPageLayout layout) {
    layout.addPerspectiveShortcut("org.eclipse.debug.ui.DebugPerspective");
  }

  public void createInitialLayout(IPageLayout layout) {
    createFolders(layout);
    addActionSets(layout);
    addShowViewShortcuts(layout);
    addNewWizardShortcuts(layout);
    addPerspectiveShotcuts(layout);
  }

}
