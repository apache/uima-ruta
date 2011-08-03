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
