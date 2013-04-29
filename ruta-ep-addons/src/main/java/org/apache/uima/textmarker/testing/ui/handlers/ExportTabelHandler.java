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

package org.apache.uima.textmarker.testing.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.textmarker.testing.ui.views.EvalDataDialog;
import org.apache.uima.textmarker.testing.ui.views.TestCasData;
import org.apache.uima.textmarker.testing.ui.views.TestPageBookView;
import org.apache.uima.textmarker.testing.ui.views.TestViewPage;
import org.apache.uima.textmarker.testing.ui.views.evalDataTable.TypeEvalData;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;


public class ExportTabelHandler extends AbstractHandler {

  public Object execute(ExecutionEvent event) throws ExecutionException {
    TestPageBookView debugView = (TestPageBookView) HandlerUtil.getActivePart(event);
    TestViewPage debugPage = (TestViewPage) debugView.getCurrentPage();
    
    TableViewer viewer = debugPage.getViewer();
    
    Shell shell = HandlerUtil.getActiveShell(event);
    
    String evalData = "Test File,Type,TP,FP,FN,Recall,Prec,F-1\n";
    
    
    ArrayList<TestCasData> dataList = (ArrayList)viewer.getInput();
    
    for (TestCasData entry : dataList) {
      Collection<TypeEvalData> col = entry.getTypeEvalData().values();
      for (TypeEvalData data : col) {
        if (!data.getTypeName().equals("Total")) {
          
        
        
        String column = entry.getPath().lastSegment() + ","
        + data.getTypeName() + "," 
        + String.valueOf(data.getTruePositives()) + "," 
        + String.valueOf(data.getFalsePositives()) + "," 
        + String.valueOf(data.getFalseNegatives()) + "," 
        + String.valueOf(data.getRecall()) + "," 
        + String.valueOf(data.getPrecision()) + "," 
        + String.valueOf(data.getFOne())
        + "\n";
        evalData = evalData+ column;
      }
      }
      
    }

    EvalDataDialog dialog = new EvalDataDialog(shell, evalData);
    dialog.open();
    
//    MessageDialog.openConfirm(shell, "Evaluation Data", text);

    return null;
  }

}
