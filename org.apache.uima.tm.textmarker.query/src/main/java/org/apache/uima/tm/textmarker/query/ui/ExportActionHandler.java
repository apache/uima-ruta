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

package org.apache.uima.tm.textmarker.query.ui;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class ExportActionHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    QueryView view = (QueryView) HandlerUtil.getActivePart(event);
    QueryComposite composite = (QueryComposite) view.getComposite();

    TableViewer viewer = composite.getResultViewer();
    Shell shell = HandlerUtil.getActiveShell(event);

    StringBuilder sb = new StringBuilder();

    List<QueryResult> dataList = (List<QueryResult>) viewer.getInput();

    for (QueryResult entry : dataList) {
      String text = entry.getText();
      text = text.replaceAll("[\\n\\r]", " ").replaceAll("[\\s]+", " ");
      sb.append(text);
      sb.append("\n");
    }

    ResultListDialog dialog = new ResultListDialog(shell, sb.toString());
    dialog.open();

    // MessageDialog.openConfirm(shell, "Evaluation Data", text);

    return null;
  }
}
