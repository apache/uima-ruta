package org.apache.uima.tm.textmarker.testing.ui.views;

import java.util.ArrayList;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TransferData;

public class ListDropAdapter extends ViewerDropAdapter {

  public ListDropAdapter(TableViewer viewer) {
    super(viewer);
  }

  @Override
  public boolean performDrop(Object data) {

    String[] filesArray = (String[]) data;
    TableViewer viewer = (TableViewer) getViewer();

    ArrayList<TestCasData> testCASes;
    if (viewer.getInput() == null) {
      testCASes = new ArrayList<TestCasData>();
    } else {
      testCASes = (ArrayList) viewer.getInput();
    }

    for (int i = 0; i < filesArray.length; i++) {
      if (filesArray[i].endsWith("xmi")) {
        TestCasData testData = new TestCasData(new Path(filesArray[i]));
        testCASes.add(testData);
      }
    }
    viewer.setInput(testCASes);
    viewer.refresh();
    return true;
  }

  @Override
  public boolean validateDrop(Object target, int operation, TransferData transferType) {
    // TODO Auto-generated method stub
    return FileTransfer.getInstance().isSupportedType(transferType);
  }

}
