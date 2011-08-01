package org.apache.uima.tm.textmarker.testing.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.uima.tm.textmarker.testing.ui.views.EvalDataDialog;
import org.apache.uima.tm.textmarker.testing.ui.views.TestCasData;
import org.apache.uima.tm.textmarker.testing.ui.views.TestPageBookView;
import org.apache.uima.tm.textmarker.testing.ui.views.TestViewPage;
import org.apache.uima.tm.textmarker.testing.ui.views.evalDataTable.TypeEvalData;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;


public class ExportTabelHandler extends AbstractHandler {

  @Override
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
