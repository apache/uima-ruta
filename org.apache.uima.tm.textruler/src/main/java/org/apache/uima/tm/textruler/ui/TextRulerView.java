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

package org.apache.uima.tm.textruler.ui;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.uima.tm.textruler.extension.TextRulerController;
import org.apache.uima.tm.textruler.extension.TextRulerControllerDelegate;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerController;
import org.apache.uima.tm.textruler.extension.TextRulerPreprocessor;
import org.apache.uima.tm.textruler.extension.TextRulerLearner.TextRulerLearnerState;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class TextRulerView extends ViewPart implements TextRulerControllerDelegate {

  public static final String ID = "org.apache.uima.tm.textmarker.ml.MainView";

  private TextRulerViewComposite viewContent;

  private IMemento memento;

  public TextRulerView() {
    super();
  }

  public void errorAlert(String message) {
    MessageBox alert = new MessageBox(getSite().getShell(), SWT.OK | SWT.ICON_ERROR);
    alert.setMessage(message);
    alert.open();
  }

  public boolean yesNoAlert(String message) {
    MessageBox alert = new MessageBox(getSite().getShell(), SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION);
    alert.setMessage(message);
    int result = alert.open();
    return result == SWT.OK; // THIS DOES NOT WORK IN OS X !!??
  }

  public void onStartStopPressed() {
    if (TextRulerController.isRunning()) {
      // if
      // (yesNoAlert("Do you really want to abort the current learning processes?"))
      // TODO does not work in OS X since alert.open always returns 256
      // (=cancel) no matter which button was pressed...
      TextRulerController.abort();
    } else {
      startPressed();
    }
    updateEnabledStatus();
  }

  public void startPressed() {
    if (!TextRulerController.isRunning()) {
      String inputDir = viewContent.getInputDirectoryPath().trim();
      String additionalDir = viewContent.getAdditionalDirectoryPath().trim();
      String preProcTMFile = viewContent.getPreprocessorTMFile().trim();

      File inputFile = new File(inputDir);
      if (!inputFile.exists()) {
        AddRemoveList.printErrorDialog("The training data directory was not found!");
        return;
      }

      File additionalFile = new File(additionalDir);
      // if (!additionalFile .exists()) {
      // AddRemoveList.printErrorDialog("The additional data directory was not found!");
      // return;
      // }

      File preFile = new File(preProcTMFile);
      if (!preFile.exists()) {
        AddRemoveList.printErrorDialog("The preprocessing script was not found!");
        return;
      }

      String[] slotNames = viewContent.getSlotNames();
      for (String string : slotNames) {
        System.out.println(string);
      }
      String[] filterArray = viewContent.getFilters();
      Set<String> filters = new HashSet<String>();
      for (String s : filterArray)
        filters.add(s);

      if (slotNames.length == 0 || inputDir.length() == 0 || preProcTMFile.length() == 0) {
        errorAlert("Please specify at least one slot type, the input directory and the preprocessing file (this is needed even if preprocessing is skipped)!");
        return;
      }
      File dir = new File(inputDir);
      if (!dir.exists() || !dir.isDirectory()) {
        errorAlert("The specified input directory could not be found!");
        return;
      }

      Object[] algs = viewContent.getAlgListViewer().getCheckedElements();

      // reset all:
      for (TextRulerLearnerController controller : TextRulerController.getAvailableControllers())
        controller.setEnabled(false);

      // enable the checked ones...
      for (Object i : algs) {
        TextRulerLearnerController c = ((TextRulerLearnerController) i);

        c.setEnabled(true);

        IViewPart resultView;

        if ((resultView = getView(TextRulerResultsView.ID, c.getID())) == null) {
          try {
            IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .showView(TextRulerResultsView.ID, ((TextRulerLearnerController) i).getID(),
                            IWorkbenchPage.VIEW_CREATE);
            ((TextRulerResultsView) view).setViewTitle(c.getName() + " - Results");
          } catch (PartInitException e) {
            e.printStackTrace();
          }
        } else {
          ((TextRulerResultsView) resultView).setViewTitle(c.getName() + " - Results");
        }
      }

      if (TextRulerController.enabledAlgorithmsCount() > 0) {
        TextRulerController.start(inputDir, additionalDir, preProcTMFile, slotNames, filters, this,
                viewContent.getCurrentAlgorithmParameters(), viewContent.getSkipPreprocessing());

        // BATCH_STUFF
        // preprocessorTMFile = preProcTMFile;
        // TextRulerView.slotNames = slotNames;
        // TextRulerView.filters = filters;
        // algParams = viewContent.getCurrentAlgorithmParameters();
        // skipPreprocessing = viewContent.getSkipPreprocessing();
        // batchNext();
      } else
        errorAlert("No algorithm has been activated for learning!");
    }
  }

  public void updateEnabledStatus() {
    boolean isRunning = TextRulerController.isRunning();
    viewContent.setEnabled(!isRunning);
  }

  public void asyncUpdateGlobalStatus(final String str) {
    viewContent.getDisplay().asyncExec(new Runnable() {
      public void run() {
        viewContent.setGlobalStatusString(str);
        updateEnabledStatus();
      }
    });
  }

  public void asyncUpdateList(final TextRulerLearnerController algController,
          final boolean ruleBaseChanged) {
    viewContent.getDisplay().asyncExec(new Runnable() {
      public void run() {
        updateList(algController, ruleBaseChanged);
      }
    });
  }

  public void updateList(TextRulerLearnerController algController, boolean ruleBaseChanged) {
    viewContent.getAlgListViewer().refresh(true, true);
    if (algController != null && ruleBaseChanged) {
      TextRulerResultsView textRulerResultsView = (TextRulerResultsView) getView(
              TextRulerResultsView.ID, algController.getID());
      if (textRulerResultsView != null) {
        textRulerResultsView.setResultContents(algController.getCurrentResultString());
      }
    }
  }

  public synchronized void algorithmDidEnd(TextRulerLearnerController algController) {
    asyncUpdateList(algController, true);

    // // BATCH_STUFF
    // if (!TextRulerController.isRunning() &&
    // !TextRulerController.shouldAbort())
    // {
    // viewContent.getDisplay().asyncExec(new Runnable(){
    // public void run() {
    // batchNext();
    // }
    // });
    // }
  }

  public static IViewPart getView(String id, String id2) {
    IViewReference viewReferences[] = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
            .getActivePage().getViewReferences();
    for (int i = 0; i < viewReferences.length; i++) {
      if (id.equals(viewReferences[i].getId()) && id2.equals(viewReferences[i].getSecondaryId())) {
        return viewReferences[i].getView(false);
      }
    }
    return null;
  }

  public synchronized void algorithmStatusUpdate(TextRulerLearnerController algController,
          String statusString, TextRulerLearnerState state, boolean ruleBaseChanged) {
    asyncUpdateList(algController, ruleBaseChanged);
  }

  public void preprocessorStatusUpdate(TextRulerPreprocessor p, String statusString) {
    asyncUpdateGlobalStatus("Preprocessing... " + statusString);
  }

  public void globalStatusUpdate(String str) {
    asyncUpdateGlobalStatus(str);
  }

  @Override
  public void createPartControl(Composite parent) {
    viewContent = new TextRulerViewComposite(parent, SWT.NULL, this);
    if (memento != null) {
      viewContent.restoreState(memento);
      memento = null;
    }
  }

  @Override
  public void setFocus() {
    viewContent.setFocus();
  }

  @Override
  public void init(IViewSite site, IMemento memento) throws PartInitException {
    this.memento = memento;
    super.init(site, memento);
  }

  @Override
  public void saveState(IMemento memento) {
    viewContent.saveState(memento);
  }

  // BATCH_STUFF
  // private static String preprocessorTMFile;
  // private static String[] slotNames;
  // private static Set<String> filters;
  // private static Map<String, Map<String, Object>> algParams;
  // private static boolean skipPreprocessing;
  //    
  // int foldNumber = 0;
  // int slotNumber = -1;
  //    
  // private static final int kFoldSize = 1;
  //    
  // public void batchNext()
  // {
  // slotNumber++;
  // if (slotNumber > slotNames.length-1)
  // {
  // slotNumber = 0;
  // foldNumber ++;
  // if (foldNumber > (kFoldSize-1))
  // {
  // TextRulerToolkit.log("DONE WITH ALL STUFF, YAAAA!");
  // return; // stop
  // }
  // }
  //    	
  // // if (foldNumber == 2 && (slotNumber == 0 || slotNumber==1)) // SKIP for
  // now!
  // // {
  // // algorithmDidEnd(null);
  // // return;
  // // }
  //    
  // TextRulerToolkit.log("******* ******* ******* ******* NEW BATCH TASK:");
  // TextRulerToolkit.log("Fold: "+foldNumber);
  // TextRulerToolkit.log("Slot: "+slotNumber);
  // TextRulerToolkit.log("******* ******* ******* *******");
  //    	
  // // String inFolder =
  // "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/Subset100/10fold/"+foldNumber+"/training/withtags";
  // // String inFolder =
  // "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/halfhalf/"+foldNumber+"/training/withtags";
  // String inFolder =
  // "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/9010_middle/"+foldNumber+"/training/withtags";
  // String[] slots = new String[slotNames.length];
  // int otherI = 1;
  // for (int i=0; i<slotNames.length; i++)
  // {
  // if (i==slotNumber)
  // slots[0] = slotNames[i];
  // else
  // {
  // slots[otherI] = slotNames[i];
  // otherI++;
  // }
  //				
  // }
  // for (String s : slots)
  // TextRulerToolkit.log("slot: "+s);
  // TextRulerToolkit.log(inFolder);
  // TextRulerController.start(inFolder, preprocessorTMFile, slots, filters,
  // this, algParams, skipPreprocessing);
  // }
}
